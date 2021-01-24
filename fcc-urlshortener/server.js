require('dotenv').config();
const express = require('express');
const cors = require('cors');
const dns = require('dns');
const app = express();
//Adding body parser to process POST parameters. 
var bodyParser = require("body-parser");
const urlDatabase = [];

// Basic Configuration
const port = process.env.PORT || 3000;

app.use(cors());

app.use('/public', express.static(`${process.cwd()}/public`));

app.get('/', function(req, res) {
  res.sendFile(process.cwd() + '/views/index.html');
});
/*
// Your first API endpoint
app.get('/api/hello', function(req, res) {
  res.json({ greeting: 'hello API' });
});*/

app.listen(port, function() {
  console.log(`Listening on port ${port}`);
});

/**
 * Function that processes the input URL checks if it's a valid url and returns the json with the shortened url.
 * @req: request object. 
 * @res: response object. 
 */ 
const urlShortener = function(req,res) {
  let url = new URL(req.body.url);
  /*let alreadyInDatabaseIndex = urlDatabase.indexOf(url);
  if(alreadyInDatabaseIndex!=-1) {
     res.json({"original_url":url,"short_url":alreadyInDatabaseIndex});   
  }
  else {*/
 //Didn't find the URL in the database, I validate it, add it to the database and return accordingly. 
  if(url.protocol != "http:" && url.protocol != "https:") {
    res.json({error:"invalid url"});
  }
  else {
    dns.lookup(url.hostname, function(err,address,family) {
      console.log("Address "+address);
      if(err) {
        res.json({error:"invalid url"});
      }
      else {
        let newUrlID = urlDatabase.push(url);
        res.json({original_url : url,short_url : newUrlID});   
      }
    });
  }
}

/**
* Function that processes shortened index and redirects to the url if it exists.
 * @req: request object. 
 * @res: response object. 
 */ 

const urlRedirector = function(req,res) {
  let index = req.params.index-1;
  console.log("ind "+index+"len "+urlDatabase.length);
  if(index >= 0 && index<urlDatabase.length) {
    let urlToRedirect = urlDatabase[index];
    res.redirect(301,urlToRedirect);
  }
  else {
    res.json({error:"invalid url"});
  }
}

//Adding the body parser processor to URL encoded requests. 
app.use(bodyParser.urlencoded({ extended:false }));

//Connecting the "new" URL to the processing function that contains all the processing logic. 
app.post('/api/shorturl/new',urlShortener);

//Connecting the :index to the processing function that contains all the processing logic. 
app.get("/api/shorturl/:index", urlRedirector);
