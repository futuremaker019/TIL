"use strict";
// typescript는 객체의 속성들을 추론해준다.
let student = {
    name: 'jake',
    course: 'tt',
    coding: 80,
    code: function () {
        console.log('brain is working hard');
    },
};
student.name = 'tell';
function calculateCoding(lostPoints) {
    return 10 - lostPoints;
}
calculateCoding(11);
