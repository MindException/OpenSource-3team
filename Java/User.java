package com.cookandroid.opensw_3team_cafereviewproject;

public class User {             //새로운 사용자들의 계정 정보를 저장하는 클래스이다.


    //이메일 이렇게 저장하는 이유가 키값으로 이렇게 하면 @와 .com 같이 보낼때 오류가 뜸
    public String address_site = "";   // @이후에 나오는 site 저장
    public String address_domain = ""; //마지막에 나오는 도메인 저장 ex) .com
    public String passwd = "";         // passwd저장
    public String name = "";           // 성함 저장
    public String nickname = "";       //계정 닉네임 저장
    public String phonenum = "";       //핸드폰 번호 저장

    User( String address_site, String address_domain,String passwd, String name, String nickname, String phonenum){     //생성자

        this.address_site = address_site;
        this.address_domain = address_domain;
        this.passwd = passwd;
        this.name = name;
        this.nickname = nickname;
        this.phonenum = phonenum;

    }

}
