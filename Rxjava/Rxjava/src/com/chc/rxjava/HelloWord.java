package com.chc.rxjava;

import java.util.ArrayList;
import java.util.List;

import com.chc.rxjava.Data.SchoolClass;
import com.chc.rxjava.Data.Student;

import rx.Observable;
import rx.Observable.OnSubscribe;
import rx.functions.Func1;
import rx.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.Subscriber;

public class HelloWord {

	private static Data mData = new Data();

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// rxCreate(); //ʹ��Create ������

		// rxjust(); //ѭ������

		// rxForm(); //ѭ��list ���ҹ���

		// rxFormIntToString(); //ѭ�����飬���ҹ������飬Ȼ��ת��ΪInt

		// rxFormIntToStringIoThared(); // ѭ�����飬���ҹ������飬Ȼ����io�߳�ת��ΪInt ��main�̴߳�ӡ

		rxflatMap();

	}

	private static void rxflatMap() {
		Observable.from(mData.getSchoolClass()) //��ȡ���еİ༶
				.flatMap(new Func1<Data.SchoolClass, Observable<Student>>() { //��SchoolClass to Student����

					@Override
					public Observable<Student> call(SchoolClass SchoolClass) {

						return Observable.from(SchoolClass.getStudents()); 
					}
				}).subscribe(new Subscriber<Student>() {

					@Override
					public void onCompleted() {

					}

					@Override
					public void onError(Throwable arg0) {

					}

					@Override
					public void onNext(Student arg0) {
						System.out.println("name=" + arg0.name + "age="
								+ arg0.age);

					}

				});

	}

	private static void rxFormIntToStringIoThared() {
		String[] test = { "0", "1", "2", "3" };

		Observable.from(test).filter(new Func1<String, Boolean>() {
			@Override
			public Boolean call(String arg0) {
				return arg0 != "0";
			}
		}).subscribeOn(Schedulers.newThread()) // �����ߣ�ר���л��߳�
				.observeOn(Schedulers.io()) // �л�Ϊio�߳�
				.map(new Func1<String, Integer>() {
					@Override
					public Integer call(String arg0) {
						return Integer.parseInt(arg0);
					}
				})
				// .observeOn(AndroidSchedulers.mainThread())
				.subscribe(new Subscriber<Integer>() {
					@Override
					public void onCompleted() {
						System.out.println("onCompleted ");
					}

					@Override
					public void onError(Throwable arg0) {
						System.out.println("onError " + arg0);
					}

					@Override
					public void onNext(Integer arg0) {
						System.out.print(Thread.currentThread().getName());
						System.out.println("onNext Integer=" + arg0);
					}
				});

	}

	private static void rxFormIntToString() {

		String[] test = { "0", "1", "2", "3", "s" };

		Observable.from(test).filter(new Func1<String, Boolean>() {
			@Override
			public Boolean call(String arg0) {
				return arg0 != "0";
			}
		}).map(new Func1<String, Integer>() {

			@Override
			public Integer call(String arg0) {

				return Integer.parseInt(arg0);
			}
		}).subscribe(new Subscriber<Integer>() {

			@Override
			public void onCompleted() {
				System.out.println("onCompleted ");

			}

			@Override
			public void onError(Throwable arg0) {
				System.out.println("onError " + arg0);

			}

			@Override
			public void onNext(Integer arg0) {
				System.out.println("onNext Integer=" + arg0);

			}
		});

	}

	private static void rxForm() {
		List<String> testList = new ArrayList();
		testList.add("0");
		testList.add("1");
		testList.add("2");
		testList.add("3");

		Observable.from(testList).filter(new Func1<String, Boolean>() {

			@Override
			public Boolean call(String arg0) { // ���˵�1
				// TODO Auto-generated method stub
				return arg0 != "1";
			}

		}).subscribe(new Subscriber<String>() {

			@Override
			public void onCompleted() {

			}

			@Override
			public void onError(Throwable arg0) {

			}

			@Override
			public void onNext(String arg0) {
				System.out.print("onNext=" + arg0);
			}
		});

	}

	private static void rxjust() {

		// ���۲��ߴ������� �۲��߶�������
		Observable.just(0, 1, 2, 3).subscribe(new Subscriber<Integer>() {

			@Override
			public void onCompleted() {
				// TODO Auto-generated method stub

			}

			@Override
			public void onError(Throwable arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onNext(Integer arg0) {
				System.out.println("onNext:=" + arg0);

			}
		});

	}

	private static void rxCreate() {
		// �������۲���
		Observable mObservable = Observable.create(new OnSubscribe<String>() {

			@Override
			public void call(Subscriber<? super String> subscriber) {
				subscriber.onNext("Hello Word!!!");
				subscriber.onCompleted();
			}
		});
		// �����۲���
		Subscriber subscriber = new Subscriber<String>() {

			@Override
			public void onCompleted() {
				System.out.print("onCompleted");
			}

			@Override
			public void onError(Throwable arg0) {

			}

			@Override
			public void onNext(String arg0) {
				System.out.print("onNext=" + arg0);
			}

		};
		mObservable.subscribe(subscriber);
	}

}
