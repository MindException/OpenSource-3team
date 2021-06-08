package com.cookandroid.opensw_3team_cafereviewproject;

import java.util.List;

public class ReviewFormat {

    //public 붙여줘야 들어간다.
    public String state;                // 도
    public String city;                 // 시
    public String day;                  //날짜
    public String title;                //카페 제목
    public String nickname;             //닉네임
    public List<String> pictures;        //사진            얘는 그냥 생성자 말고 따로 추가하기
    public String review;               //리뷰
    public float score;                   //별점
    public int likes;                   //좋아요 수
    public int hates;                   //싫어요 수

    //이 값이 null값이면 어차피 서버에 안 들어가기 때문에 push값을 나중에 여기다가 넣어준다.
    private String myfollowid;
    private String mylocalid;

    //생성자들

    ReviewFormat(){     //기본 생성자

    }

    //리뷰 작성에 쓰이는 것
    ReviewFormat(String state, String city, String day,String title ,String nickname, List<String> pictures,
                 String review, float socre){

        this.state = state;
        this.city = city;
        this.day = day;
        this.nickname = nickname;
        this.title = title;
        this.review = review;
        this.pictures = pictures;
        this.score = socre;
        this.likes = 0;
        this.hates = 0;

    }

    //리뷰 불러오기할 때 쓰이는 것(여기는 좋아요 수와 싫어요 수가 있다.)
    ReviewFormat(String state, String city, String day, String nickname, String review, List<String> picture,
             int socre, int likes, int hates){

        this.state = state;
        this.city = city;
        this.day = day;
        this.nickname = nickname;
        this.review = review;
        this.pictures = picture;
        this.score = socre;
        this.likes = likes;
        this.hates = hates;

    }

    //바이너리 바이트 배열을 스트링 변환
    public static String byteArrayToBinaryString(byte[] b) {

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < b.length; ++i) {

            sb.append(byteToBinaryString(b[i]));

        }
        return sb.toString();

    }

    // 바이너리 바이트를 스트링으로
    public static String byteToBinaryString(byte n) {

        StringBuilder sb = new StringBuilder("00000000");

        for (int bit = 0; bit < 8; bit++) {

            if (((n >> bit) & 1) > 0) {

                sb.setCharAt(7 - bit, '1');
            }
        }
        return sb.toString();
    }

    void setfollowid(String key){  //자신의 서버 키값을 저장

        this.myfollowid = key;

    }

    void setlocalid(String key){  //자신의 서버 키값을 저장

        this.mylocalid = key;

    }

    String getfollowid(){  //자신의 서버 키값을 내 보냄.

        return this.myfollowid;

    }

    String getlocalid(){  //자신의 서버 키값을 내 보냄.

        return this.mylocalid;

    }

    //이제 다시 사진으로 바꿔주는 것
    // 스트링을 바이너리 바이트 배열로
    public static byte[] binaryStringToByteArray(String s) {
        int count = s.length() / 8;
        byte[] b = new byte[count];
        for (int i = 1; i < count; ++i) {
            String t = s.substring((i - 1) * 8, i * 8);
            b[i - 1] = binaryStringToByte(t);
        }
        return b;
    }

    // 스트링을 바이너리 바이트로
    public static byte binaryStringToByte(String s) {
        byte ret = 0, total = 0;
        for (int i = 0; i < 8; ++i) {
            ret = (s.charAt(7 - i) == '1') ? (byte) (1 << i) : 0; total = (byte) (ret | total);
        }
        return total;
    }














}
