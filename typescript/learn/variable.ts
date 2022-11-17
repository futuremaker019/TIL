let studentID: number = 11;
let studentName: string = 'jeck';
let age: number = 12;
let courseComplete: boolean = false;

let student1 = {
  studentID: 11,
  studentName: 'string',
  age: 12, // 속성명 뒤에 ? 를 작성하여 age를 쓰지않아도 에러가 발생하지 않는다.
  courseComplete: false,
};

// 인터페이스를 정의하여 반환값으로 받을수 있다.
interface Student {
  studentID: number;
  studentName: string;
  age?: number; // 속성명 뒤에 ? 를 작성하여 age를 쓰지않아도 에러가 발생하지 않는다.
  courseComplete: boolean;
  // addComment (comment: string): string;
  addComment?: (comment: string) => string; // string 값을 반환하는 메서드
}

function getStudentDetails(studentID: number): Student | null {
  // return null;
  return {
    studentID: 11,
    studentName: 'string',
    // age: 12,
    courseComplete: true,
  };
}

function saveStudentDetails(student: Student): void {}

saveStudentDetails(student1);
