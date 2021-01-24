const express = require('express')
const app = express()
const cors = require('cors')
const mongoose = require("mongoose");
require('dotenv').config();
var bodyParser = require("body-parser");

app.use(cors());
app.use(express.static('public'));

mongoose.connect(process.env.MONGO_URI, { useNewUrlParser: true, useUnifiedTopology: true });

app.get('/', (req, res) => {
  res.sendFile(__dirname + '/views/index.html')
});

const listener = app.listen(process.env.PORT || 3000, () => {
  console.log('Your app is listening on port ' + listener.address().port)
})

const userSchema = new mongoose.Schema({
    username: String,
    log: [
      {
        description:String,
        duration:Number,
        date:Date,
      }
    ]
});
const User = mongoose.model("User", userSchema);

//Adding the body parser processor to URL encoded requests. 
app.use(bodyParser.urlencoded({ extended:false }));

/*app.use(function(req,res, next) {
  console.log("Path: "+req.path);
  console.log("Params: ");
  console.log(req.params);
  console.log("Query string: ");
  console.log(req.query);
  next();
}); */


/** 
 * Create a new user
 * You can POST to /api/exercise/new-user with form data username to create a new user. The returned response will be an object with username and _id properties.
 */
app.post("/api/exercise/new-user",function(req,res) {
  let uname = req.body.username;
  var user = new User({"username":uname});
  user.save(function(err,obj) {
    if(err) {
      console.log(err);
      res.log("Username already taken");
    }
    else {
      res.json({username:uname,_id:obj._id});
    }  
  });
});

/** 
 * Create a new exercise
 * You can POST to /api/exercise/add with form data userId=_id, description, duration, and optionally date. If no date is supplied, the current date will be used. The response returned will be the user object with the exercise fields added.
 */
app.post("/api/exercise/add",function(req,res) {
  let uid = req.body.userId;
  let description = req.body.description;
  let duration = new Number (req.body.duration);
  let date = (req.body.date == "" || req.body.date === undefined) ? new Date():new Date(req.body.date);
  User.findById(uid,function(err,result) {
    if(err) {
      res.log("User not found");
    }
    else {
      result.log.push({
        "description":description,
        "duration":duration,
        "date":date
      }); 
      result.save(function(err,obj){
        if(err) {
          res.json({"err":err});
        }
        else {
          //res.json({"_id":uid,"username":"gartija","date":date,"duration":duration,"description":description});
          res.json({"_id":uid,
                    "username":obj.username,"date":date.toDateString(),
                    "duration":duration,"description":description});
        }
      });
    }
  });
});

/** 
 * GET users
 * You can make a GET request to api/exercise/users to get an array of all users. Each element in the array is an object containing a user's username and _id.
 */
app.get("/api/exercise/users",function(req,res) {
  let query = User.find({}).select({ _id: 1, username: 1 });
  query.exec(function(err,foundUsers) {
    if(err) {
      res.log("No users found");
    }
    else {
      res.json(foundUsers);
    }
  });
});


/** 
 * GET users's exercise log
 * You can make a GET request to /api/exercise/log with a parameter of userId=_id to retrieve a full exercise log of any user. The returned response will be the user object with a log array of all the exercises added. Each log item has the description, duration, and date properties.
 * You can add from, to and limit parameters to a /api/exercise/log request to retrieve part of the log of any user. from and to are dates in yyyy-mm-dd format. limit is an integer of how many logs to send back.
 */
app.get("/api/exercise/log",function(req,res) {
  let userId = req.query.userId;
  let from = req.query.from != undefined ? new Date(req.query.from):undefined;
  console.log(req.query.from+" "+from);
  let to = req.query.to != undefined ? new Date(req.query.to):undefined;
  let limit = req.query.limit;
  let query = User.findOne({"_id":userId});
  query.exec(function(err,result) {
    if(err) {
      console.log("User not found");
    }
    else {
      var results = [];
      if(from != undefined && to != undefined) {
        let exercises = result.log;
        for (let i = 0 ; i < exercises.length ; i++) {
          if(exercises[i].date>from && exercises[i].date<to) {
            results.push(exercises[i]);
          }
        }
      }
      else {
        results = result.log;
      }
      if (limit != undefined) {
        results = results.splice(0,limit);
      }
      //console.log("Filtered result "+results);
      //{"_id":"60021fda0aa40e05f2b89325","username":"yema5","from":"Tue Jan 01 1980","to":"Tue Jan 01 2002","count":1,"log":[{"description":"d2","duration":2,"date":"Mon Jan 01 2001"}]}
      res.json({"_id":result._id,
      "username":result.username,
      "from":from,
      "to":to,
      "count":results.length,
      "log":results});
    }
  });
  //res.json({count:0});
});

/**
  * A request to a user's log (/api/exercise/log) returns an object with a count property representing the number of exercises returned.
 */
app.get("/api/exercise/log/:username",function(req,res) {
  let uname = req.params.username;
  console.log(uname);
  User.findOne({username:uname},function(err,obj) {
    if(err) {
      console.log("Error");
    }
    else {
      console.log(obj);
      res.json({count:obj.log.length})
    }
  });
});


