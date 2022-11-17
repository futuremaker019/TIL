"use strict";
let studentID = 11;
let studentName = 'jeck';
let age = 12;
let courseComplete = false;
let student1 = {
    studentID: 11,
    studentName: 'string',
    age: 12,
    courseComplete: false,
};
function getStudentDetails(studentID) {
    // return null;
    return {
        studentID: 11,
        studentName: 'string',
        // age: 12,
        courseComplete: true,
    };
}
function saveStudentDetails(student) { }
saveStudentDetails(student1);
