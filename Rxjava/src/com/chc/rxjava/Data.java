package com.chc.rxjava;

public class Data {
	
	public Data() {
		initData();	
	}

	private static SchoolClass[] mSchoolClasses = new SchoolClass[2];

	private void initData() {

		Student[] student = new Student[5];
		for (int i = 0; i < 5; i++) {
			Student s = new Student("ถþนท" + i, "17");
			student[i] = s;
		}
		mSchoolClasses[0] = new SchoolClass(student);

		Student[] student2 = new Student[5];
		for (int i = 0; i < 5; i++) {
			Student s = new Student("ะกร๗" + i, "27");
			student2[i] = s;
		}
		mSchoolClasses[1] = new SchoolClass(student2);

	}

	public SchoolClass[] getSchoolClass() {
		return mSchoolClasses;
	}

	static class SchoolClass {
		Student[] stud;

		public SchoolClass(Student[] s) {
			this.stud = s;
		}

		public Student[] getStudents() {
			return stud;
		}
	}

	static class Student {
		String name;
		String age;

		public Student(String name, String age) {
			this.name = name;
			this.age = age;
		}
	}
}
