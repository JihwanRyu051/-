# :iphone: Wake up your brain 프로젝트
### [201712159 조현우](https://github.com/hyun98)<br>
### [201624567 전민기](https://github.com/JUNMinGi)<br>
### [201724461 류지환](https://github.com/JihwanRyu051)<br><br>
---
### :scroll: 목차
#### 1. 개발 배경 및 목적
#### 2. 개발 환경 및 언어
#### 3. 시스템 구성 및 아키텍처
#### 4. 프로젝트 주요기능
#### 5. 기타

---

## 1. 개발 배경 및 목적
<img width="223" alt="image" src="https://user-images.githubusercontent.com/68914294/122731659-7abcb380-d2b6-11eb-9257-50f9b39bb233.png">

- 상단 그림은 온라인 패널 서비스 나우에서 2020년 약 3만명을 대상으로 실시한 모바일게임 장르 선호도 설문조사 결과입니다. 이를 보면 롤플레잉 게임에 이어 퍼즐,보드게임이 전체 2위를 기록함을 알 수 있습니다. 

-	저희는 기존의 게임을 구현하는 것에 더하여 시각적으로 자신의 기록을 확인할 수 있는 그래프 기능과, Player간에 서로 소통할 수 있는 채팅 기능을 도입하기로 했습니다. 

---

## 2. 개발 환경 및 언어
Android Studio 4.2 (매우 권장),  JAVA,  Firebase

---

## 3. 시스템 구성 및 아키텍쳐
<img width="429" alt="image" src="https://user-images.githubusercontent.com/68914294/122732490-4bf30d00-d2b7-11eb-96d0-50a3fd2f838d.png">

---

## 4. 프로젝트 주요기능
### 1	Sequence game
1.	3 X 3 크기의 퍼즐
2.	컴퓨터는 9개의 카드 중 하나를 무작위로 선택하고 시퀀스 배열에 추가
3.	현재 시퀀스 배열에 있는 카드를 순서대로 1초에 하나씩 뒤집어가며 User에게 보여줌
4.	카드가 순서대로 다 보여지고 난 후 User는 보여진 순서대로 카드를 선택함
5.	끝까지 성공 했다면 (2)로 돌아감
6.	실패했다면 현재 점수(z-score : (level – 평균) /표준편차)를 보여주고 게임 종료
7.	z-score를 데이터베이스로 전송

<img width="142" alt="image" src="https://user-images.githubusercontent.com/68914294/122732924-ab511d00-d2b7-11eb-8906-59086dae14ee.png"> <img width="142" alt="image" src="https://user-images.githubusercontent.com/68914294/122736951-8494e580-d2bb-11eb-9cfd-d1945e5a5aaa.png">

---

### 2	Match game
1.	3 X 4 크기의 퍼즐
2.	카드를 클릭하면 해당 카드의 태그를 저장, 앞면 카드의 이미지를 노출 후 다시 뒷면 이미지를 노출
3.	두 장을 한 쌍으로, 선택한 두 카드의 태그가 동일하면 Score를 증가시키고 덱에서 카드를 제거, 동일하지 않다면 아무런 변화없이 게임 진행
4.	모든 쌍을 제거하면 게임 종료
5.	Clicked 평균을 통해서 구한 z-score를 데이터베이스로 전송

<img width="144" alt="image" src="https://user-images.githubusercontent.com/68914294/122737010-95455b80-d2bb-11eb-9fa7-294fd8fc0088.png"> <img width="143" alt="image" src="https://user-images.githubusercontent.com/68914294/122737036-9bd3d300-d2bb-11eb-9534-e551f7c3b9f0.png">

---
  
### 3	Verbal game
1.	1600개의 단어 중 무작위로 300개 단어를 가져와서 순서대로 보여줌
2.	현재 게임에서 해당 단어를 봤으면 seen, 처음 보는 단어라면 new를 선택
3.	Score가 높아질수록 이미 본 단어가 나올 확률 조금씩 증가
4.	이미 나왔는데 new를 선택하거나 나온 적 없는데 seen을 선택하면 lives가 1감소
5.	Lives가 0이 되면 게임 종료
6.	임의의 Score 평균을 통해서 구한 z-score를 데이터베이스로 전송

<img width="142" alt="image" src="https://user-images.githubusercontent.com/68914294/122737087-a7bf9500-d2bb-11eb-9beb-12087af381e9.png"> <img width="142" alt="image" src="https://user-images.githubusercontent.com/68914294/122737100-ab531c00-d2bb-11eb-8ef7-f07c204211ac.png">

---
    
### 4	Response game
1.	첫 화면을 터치하면 빨간색 화면이 나온다.
2.	빨간색 화면에서 2~4초 사이의 임의의 시간에 초록 화면으로 바뀐다.
3.	초록 화면으로 바뀌기 전에 터치를 한다면 터치 시간은 기록되지 않고 다시 기회를 얻는다.
4.	초록 화면을 터치하면(누르는 순간 시간 기록) 반응속도가 측정된다.
5.	총 5번 반복 후 게임은 종료된다.
6.	반응속도에 따른 z-score를 데이터베이스로 전송.

<img width="130" alt="image" src="https://user-images.githubusercontent.com/68914294/122737144-b6a64780-d2bb-11eb-8311-2aad18dbc26d.png"> <img width="128" alt="image" src="https://user-images.githubusercontent.com/68914294/122737155-b9a13800-d2bb-11eb-976b-4fa5fb69a4f8.png"> <img width="129" alt="image" src="https://user-images.githubusercontent.com/68914294/122737164-bc9c2880-d2bb-11eb-9b4d-eca1559c194d.png"> <img width="120" alt="image" src="https://user-images.githubusercontent.com/68914294/122737183-bf971900-d2bb-11eb-98ac-3d3877926926.png">

---

### 5	Chatting app
1.	Chat Card View를 누르면 채팅을 할 수 있는 화면으로 이동한다.
2.	채팅방의 이름과 자신의 닉네임을 설정하고 입장을 누르면 해당 이름을 가진 채팅방이 있다면 입장하고, 없다면 새로 개설한다.
3.	실시간으로 자유롭게 채팅기능을 이용할 수 있다..

<img width="145" alt="image" src="https://user-images.githubusercontent.com/68914294/122737221-cb82db00-d2bb-11eb-88ef-0aa9ccbc10c9.png"> <img width="146" alt="image" src="https://user-images.githubusercontent.com/68914294/122737230-cde53500-d2bb-11eb-9fdf-73e4e66c609e.png"> <img width="166" alt="image" src="https://user-images.githubusercontent.com/68914294/122737295-dccbe780-d2bb-11eb-81f1-7b256cc52d10.png">

위와 같은 형태로 firebase에 대화 내용 데이터가 저장되며, 채팅방마다, 채팅마다 구분하여 메시지 및 유저정보 데이터가 저장됨.

---

6	Main page
1.	개임 별 Card View를 누르면 해당 게임으로 이동
2.	메뉴의 게임을 누르면 해당 게임으로 이동

<img width="146" alt="image" src="https://user-images.githubusercontent.com/68914294/122737337-e6ede600-d2bb-11eb-8b0d-06693516aaa6.png"> <img width="146" alt="image" src="https://user-images.githubusercontent.com/68914294/122737348-e8b7a980-d2bb-11eb-9476-5c60ddcbdc29.png">

---

### 데이터베이스 구조
#### 1	전체 개요
<img width="264" alt="image" src="https://user-images.githubusercontent.com/68914294/122737493-0dac1c80-d2bc-11eb-848a-bb4b0600842a.png">
1.	게임 노드(Wake up Brain)과 채팅 노드로 분류
2.	게임 노드는 유저계정 – IDToken – 유저정보(gMap, ID, Token, 비밀번호)로 구성
3.	채팅 노드는 채팅방 – Token – 채팅 (메시지 및 유저명)

#### 2	gMap
<img width="231" alt="image" src="https://user-images.githubusercontent.com/68914294/122737502-10a70d00-d2bc-11eb-87b1-496a02d92190.png">

1.	유저별 게임 정보를 저장하는 노드
2.	게임 – 게임 정보 (최고점수, 총합, 플레이 횟수, 게임명)으로 구성

---

### 로그인/아웃 및 회원 가입/탈퇴
#### 1	로그인/아웃

<img width="114" alt="image" src="https://user-images.githubusercontent.com/68914294/122737590-287e9100-d2bc-11eb-9593-b8dc8a31dd39.png"> <img width="112" alt="image" src="https://user-images.githubusercontent.com/68914294/122737594-2a485480-d2bc-11eb-986f-457b30e8a05b.png">

1.	로그인 버튼 클릭 시 text view에 적힌 이메일과 비밀번호 정보를 데이터베이스로 전송 후 파이어베이스 Auth Api를 활용해 인증
2.	인증 성공 시 성공 메시지 출력과 함께 메인 화면으로 이동,
인증 실패 시 실패 메시지 출력
3.	메인화면의 네비게이션바에 로그아웃 버튼 클릭 시 파이어베이스 Auth Api를 통해 로그아웃 후 로그인 화면으로 이동

#### 2	회원 가입/탈퇴

<img width="107" alt="image" src="https://user-images.githubusercontent.com/68914294/122737644-38967080-d2bc-11eb-9ddb-ba3418896de9.png"> <img width="106" alt="image" src="https://user-images.githubusercontent.com/68914294/122737648-3a603400-d2bc-11eb-9c98-425247ceb9d9.png"> <img width="107" alt="image" src="https://user-images.githubusercontent.com/68914294/122737655-3c29f780-d2bc-11eb-83c3-1573dde9ab5b.png">

1.	로그인 화면에서 회원가입 버튼 클릭 시 회원가입 화면으로 이동
2.	가입 완료 버튼 클릭 시 text view에 적힌 이메일과 비밀번호 정보를 파이어베이스 Auth pi를 통해 전송 후 계정 등록
3.	gMap을 초기화, gMap과 유저 이메일, 비밀번호, ID Token을 유저 객체로 묶어 데이터베이스에 기록
4.	회원가입 성공 메시지 출력 후 로그인 화면으로 이동
5.	메인 화면의 네비게이션 바에 Delete Account 버튼 클릭 시 회원탈퇴 동의 여부 확인을 위한 알람 출력
6.	동의할 경우 파이어베이스 Auth에 등록된 유저 정보 삭제 및 데이터 베이스에 저장된 유저 객체 삭제
7.	삭제 성공 시 삭제 메시지 출력 후 로그인 화면으로 이동



### 데이터 시각화 기능 프로필 연동
[MPAndroidChart](https://github.com/PhilJay/MPAndroidChart)를 이용해 레이더 차트를 그리는 데이터 시각화 기능을 완성하였다.
프로필 페이지에 들어가면 지금까지 자신이 플레이한 게임의 최고 점수와 평균 점수를 파이어베이스에서 받아와 레이더 그래프를 통해서 시각적으로 볼 수 있다. 빨간 그래프는 본인의 최고 점수, 파란 그래프는 본인의 점수들의 평균을 나타낸다. 그래프는 실시간으로 업데이트 된다.

<img width="137" alt="image" src="https://user-images.githubusercontent.com/68914294/122737711-4d730400-d2bc-11eb-8eb2-463ea6bd4a4b.png"> <img width="197" alt="image" src="https://user-images.githubusercontent.com/68914294/122737723-4fd55e00-d2bc-11eb-8914-78799ec39e93.png">

---
   
### 데이터 베이스(firebase) 연동
|-----|User1|User2|...|UserN|
|------|-----|-----|-----|-----|
|Matchgame|80|67|0|78|
|Sequencegame|80|45|0|74|
|verbalgame|85|90|0|92|
|responsegame|60|77|0|45|

위와 같은 User테이블을 Firebase에 생성해서 게임을 마무리 한 후 데이터 베이스에 적절한 정보를 저장

---

|전민기|조현우|류지환|
|------|---|---|
|클라이언트/서버 통신 설계, 데이터 시각화 기능 구현,Firebase를 활용한 채팅 프로그램 구현|전반적인 UI설계, Main page 구현, 게임 알고리즘 설계 및 구현|firebase를 활용한 데이터베이스 구현, 회원가입 및 탈퇴, 로그인, 기능 구현|
