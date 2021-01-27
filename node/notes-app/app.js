// section 3 : Importing Node.js Core Modules

// const fs = require("fs");

// fs.writeFileSync("notes.txt", "my name is Jung.");

// Challenge: Append a message to notes.txt
//
// 1. Use appendFileSync to append to the file
// 2. Run the script
// 3. Check your work by opening the file viewing the appended text

// fs.appendFileSync("notes.txt", "this is test");

// section 3 : Importing Your Own Files
// const add = require("./utils.js");

// const sum = add(4, -2);

// console.log(sum);

// Challenge: Define and usr a function in a new file
//
// 1. Create a new file called notes.js
// 2, Create getNotes function that returns "You notes..."
// 3. Export getNotes function
// 4. from app.js load in and all the function printing message to console.

// const getNotes = require("./notes.js");

// const msg = getNotes();

// // console.log(getNotes());
// console.log(msg);

// Importing npm Modules

// const validator = require("validator");
// console.log(validator.isEmail("andrew@example.com"));

// Challenge use the chalk library in your project
// 1. Install version 2.4.1 of chalk
// 2. Load chalk info app.js
// 3. Use it to print the strign "Success" to the console in green
// 4. Test your work
// Bonus : Use docs to mess around other styles, Make text bold and inversed.

// console.log(chalk.blue("hello world"));
// console.log(chalk.red.inverse.bold("hello world"));

// 16.Argument Parsing with Yargs:Part1
const chalk = require('chalk');
const yargs = require('yargs');
const getNotes = require('./notes.js');

// Customize yargs version
yargs.version('1.1.0');

// Create add command
yargs.command({
  command: 'add',
  describe: 'Add a new note',
  handler: function () {
    console.log('Adding a new note!');
  },
});

// Create remove command
yargs.command({
  command: 'remove',
  describe: 'Remove a note',
  handler: function () {
    console.log('Remove the note');
  },
});

// Chanlleange: Add two new commands

// 1. Setup command to support "list" command (print placeholder massage for now)
// 2. Setup command to support "read" command (print placeholder massage for now)
// 3. Test your work by running both commands and ensure corrent output

// add remove, read, list
// Create read command
yargs.command({
  command: 'read',
  describe: 'Read a note',
  handler: function () {
    console.log('Removing the note');
  },
});

// Create list command
yargs.command({
  command: 'list',
  describe: 'list a note',
  handler: function () {
    console.log('Listing the note');
  },
});

console.log(process.argv);
console.log(yargs.argv);
