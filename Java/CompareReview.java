package com.cookandroid.opensw_3team_cafereviewproject;

import java.util.Comparator;
import java.util.StringTokenizer;

public class CompareReview<T extends ReviewFormat> implements Comparator<T> {
    //Arraylist에서 sort함수를 쓰기 위한 클래스이다.


    @Override
    public int compare(T o1, T o2){
        //양수이면 o2 즉, 자기 자신이 더 크다.
        //음수이면 o1 즉, 비교치가 더 크다.

        String date1;
        StringTokenizer st1 = new StringTokenizer(o1.day, "-");     //날짜를 다 변환해서 합친다.
        String year1 = st1.nextToken();
        String month1 = st1.nextToken();
        String day1 = st1.nextToken();
        date1 = year1 + month1 + day1;
        int pdate1 = Integer.parseInt(date1);              //다 합쳐서 정수로 변환시킨다.

        String date2;
        StringTokenizer st2 =  new StringTokenizer(o2.day, "-");
        String year2 = st2.nextToken();
        String month2 = st2.nextToken();
        String day2 = st2.nextToken();
        date2 = year2 + month2 + day2;
        int pdate2 = Integer.parseInt(date2);               //동일하게 한다.


        return (pdate1 > pdate2) ? -1 : (pdate1 == pdate2) ? 0 : 1;

    }

}
