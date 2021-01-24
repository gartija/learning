// server.js
// where your node app starts

// init project
var express = require('express');
var app = express();

// enable CORS (https://en.wikipedia.org/wiki/Cross-origin_resource_sharing)
// so that your API is remotely testable by FCC 
var cors = require('cors');
app.use(cors({optionsSuccessStatus: 200}));  // some legacy browsers choke on 204

// http://expressjs.com/en/starter/static-files.html
app.use(express.static('public'));

// http://expressjs.com/en/starter/basic-routing.html
app.get("/", function (req, res) {
  res.sendFile(__dirname + '/views/index.html');
});


// your first API endpoint... 
app.get("/api/hello", function (req, res) {
  res.json({greeting: 'hello API'});
});

/**
 * Implementation of the timestamp server
 */ 
 
 //When the request is empty
 app.get("/api/timestamp", function (req, res) {
  var date;
  date = new Date(); 
  res.json({unix:date.valueOf(),utc:date.toUTCString()});
});

//When the request comes with a value
app.get("/api/timestamp/:date", function (req, res) {
  let inputDate = req.params.date;
  var date = new Date(inputDate);  
  if(!isNaN(inputDate)) {
    date = new Date(new Number(inputDate));  
  }
  
  if(date=="Invalid Date") {
    res.json({error: "Invalid Date"});    
  }
  else {
    res.json({unix:date.valueOf(),utc:date.toUTCString()});  
  }
});


// listen for requests :)
var listener = app.listen(process.env.PORT, function () {
  console.log('Your app is listening on port ' + listener.address().port);
});
