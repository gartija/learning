require('dotenv').config();

let mongoose = require("mongoose");

mongoose.connect(process.env.MONGO_URI, { useNewUrlParser: true, useUnifiedTopology: true });

/**
 * MongoDB and Mongoose - Create a Model
 *https://www.freecodecamp.org/learn/apis-and-microservices/mongodb-and-mongoose/create-a-model
 */
const personSchema = new mongoose.Schema({
    name:  String, 
    age: Number,
    favoriteFoods: [String]
});

const Person = mongoose.model("Person", personSchema);

/**
 * MongoDB and Mongoose - Create and Save a Record of a Model
 * https://www.freecodecamp.org/learn/apis-and-microservices/mongodb-and-mongoose/create-and-save-a-record-of-a-model
 */
const createAndSavePerson = (done) => {
  var person = new Person({name:"TestName",age:0,favoriteFoods:["TestFav1","TestFav2"]});
  person.save(done);
  console.log(person);
};

/**
 *MongoDB and Mongoose - Create Many Records with model.create()
 * https://www.freecodecamp.org/learn/apis-and-microservices/mongodb-and-mongoose/create-many-records-with-model-create
 */
const createManyPeople = (arrayOfPeople, done) => {
  Person.create(arrayOfPeople,done);
  /* Instead of calling done callback from here, I send the callback to the create method from the model and it works. 
  done(null,arrayOfPeople);*/
};

/**
 * MongoDB and Mongoose - Use model.find() to Search Your Database
 * https://www.freecodecamp.org/learn/apis-and-microservices/mongodb-and-mongoose/use-model-find-to-search-your-database
 */ 
const findPeopleByName = (personName, done) => {
  Person.find({name:personName},done);
};

/**
 * MongoDB and Mongoose - Use model.findOne() to Return a Single Matching Document from Your Database
 * https://www.freecodecamp.org/learn/apis-and-microservices/mongodb-and-mongoose/use-model-findone-to-return-a-single-matching-document-from-your-database
 */ 
const findOneByFood = (food, done) => {
  let result=Person.findOne({favoriteFoods:food},done);
};

/**
 * MongoDB and Mongoose - Use model.findById() to Search Your Database By _id
 * https://www.freecodecamp.org/learn/apis-and-microservices/mongodb-and-mongoose/use-model-findbyid-to-search-your-database-by-id
 */ 
const findPersonById = (personId, done) => {
  Person.findById(personId, done);
};

/**
 * MongoDB and Mongoose - Perform Classic Updates by Running Find, Edit, then Save
 * https://www.freecodecamp.org/learn/apis-and-microservices/mongodb-and-mongoose/perform-classic-updates-by-running-find-edit-then-save
 */ 
const findEditThenSave = (personId, done) => {
  const foodToAdd = "hamburger";
  Person.findById(personId,  (err, foundP) => { 
      if (err){ 
        console.log(err); 
      } 
      else{ 
        foundP.favoriteFoods.push(foodToAdd); 
        foundP.save(done);
      } 
  });
};

/**
 * MongoDB and Mongoose - Perform New Updates on a Document Using model.findOneAndUpdate()
 * https://www.freecodecamp.org/learn/apis-and-microservices/mongodb-and-mongoose/perform-new-updates-on-a-document-using-model-findoneandupdate
 */ 
const findAndUpdate = (personName, done) => {
  const ageToSet = 20;
  
  Person.findOneAndUpdate({name:personName},{age:ageToSet}, { new: true },(err,p) => {
    if(err){
      console.log(err);
    } 
    else {
      p.save(done);
    }
  });
};
/**
 * MongoDB and Mongoose - Delete One Document Using model.findByIdAndRemove
 * https://www.freecodecamp.org/learn/apis-and-microservices/mongodb-and-mongoose/delete-one-document-using-model-findbyidandremove
 */ 
const removeById = (personId, done) => {
  Person.findByIdAndRemove(personId,done);
  //done(null /*, data*/);
};

/**
 * MongoDB and Mongoose - Delete Many Documents with model.remove()
 * https://www.freecodecamp.org/learn/apis-and-microservices/mongodb-and-mongoose/delete-many-documents-with-model-remove
 */
const removeManyPeople = (done) => {
  const nameToRemove = "Mary";
  //remove is deprecated, would be good to check with delete. 
  Person.remove({name:nameToRemove},done);
};

/**
 * MongoDB and Mongoose - Chain Search Query Helpers to Narrow Search Results
 * https://www.freecodecamp.org/learn/apis-and-microservices/mongodb-and-mongoose/chain-search-query-helpers-to-narrow-search-results
 */ 
const queryChain = (done) => {
  const foodToSearch = "burrito";
  let query = Person.find({favoriteFoods:foodToSearch}).sort({name:1}).limit(2).select({name:1,favoriteFoods:1});
  //Testing with explicit callback implementation, just to learn :)
  query.exec((err,data) => {
    if(err){ 
      console.err(err);
    }
    else {
      console.log(data);
      done(null,data);    
    }
  });
};

/** **Well Done !!**
/* You completed these challenges, let's go celebrate !
 */

//----- **DO NOT EDIT BELOW THIS LINE** ----------------------------------

exports.PersonModel = Person;
exports.createAndSavePerson = createAndSavePerson;
exports.findPeopleByName = findPeopleByName;
exports.findOneByFood = findOneByFood;
exports.findPersonById = findPersonById;
exports.findEditThenSave = findEditThenSave;
exports.findAndUpdate = findAndUpdate;
exports.createManyPeople = createManyPeople;
exports.removeById = removeById;
exports.removeManyPeople = removeManyPeople;
exports.queryChain = queryChain;
