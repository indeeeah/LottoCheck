# LottoCheck
--------
## Contents
1. [설계의 주안점](#설계의-주안점)
2. [Using](#Using)
3. [LottoCheck 기능 설명](#LottoCheck-기능-설명)
4. [추후 구현 예정 기능](#추후-구현-예정-기능)
--------
## 설계의 주안점
1. Swing으로만 UI 구성
2. JSON으로 로또 번호 데이터 받아오기
3. 입력한 번호와 회차를 통한 데이터 비교
4. 1~45의 중복되지 않는 번호들로만 이루어질 수 있는 유효성 검사
5. 수에 따른 background-color 변화
--------
## Using
1. FrontEnd - Java Swing
2. BackEnd - Java(JDK 1.8)
3. OS - MacOs
4. Library&API - json-simple-1.1.1.jar
5. IDE - Eclipse(EE, 2020-06)
6. Server - Java Application
7. Cl - Github, git
--------
## LottoCheck 기능 설명
### [로또 번호 확인 기능]

#
Lotto.java
``` java
JsonReader jr = new JsonReader();
JSONObject jo = jr.connectionUrlToJson(turnTxt.getText());
String[] right = new String[MAX_CNT]; // 로또번호 맞는지 입력한 것 저장
int nCnt = 0; // 맞는 숫자 개수
int bCnt = 0; // 맞는 숫자 개수(보너스 번호. 2등)


for (int i = 0; i < MAX_CNT; i++) {

    // 로또 번호는 drwtNo1, drwtoNo2.. 순으로 나와있다.
    String strNo = String.valueOf(jo.get("drwtNo" + (i + 1)));

    // 공 색깔 바꾸기
    btn[i].setText(strNo);
    int a = Integer.parseInt(strNo);
    if (a > 40) {
        btn[i].setBgColor(Color.magenta);
    } else if (a > 30) {
        btn[i].setBgColor(Color.cyan);
    } else if (a > 20) {
        btn[i].setBgColor(Color.lightGray);
    } else if (a > 10) {
        btn[i].setBgColor(Color.pink);
    } else {
        btn[i].setBgColor(Color.orange);
    }
    btn[6].setBgColor(Color.green);
}
btn[MAX_CNT].setText(String.valueOf(jo.get("bnusNo")));

```
JsonReader.java
``` java
URL url = new URL("https://www.dhlottery.co.kr/common.do?method=getLottoNumber&drwNo=" + turn);

```
+ JsonReader.java 에서 로또 번호를 긁어옵니다.
+ 로또 번호는 drwtNo1, drwtNo2.. 로 되어있기 때문에 for문을 돌려 해당 회차의 당첨 번호들을 조회하였습니다.
#

1. 로또 번호, 회차 정상 입력

#
Lotto.java
``` java
if (nCnt == 6) {
    rankLabel.setText("축하합니다 1등입니다!");
} else if (nCnt == 5 && bCnt == 1) {
    rankLabel.setText("축하합니다 2등입니다!");

} else if (nCnt == 5) {
    rankLabel.setText("축하합니다 3등입니다!");

} else if (nCnt == 4) {
    rankLabel.setText("축하합니다 4등입니다!");

} else if (nCnt == 3) {
    rankLabel.setText("축하합니다 5등입니다!");

} else {
    rankLabel.setText("다음 기회를 노려보세요.");
}
```

+ 당첨 개수에 따라 나타나는 text를 다르게 하였습니다.
+ 2등은 보너스 번호(bCnt)를 맞추어야 하기 때문에 해당 조건을 주었습니다.
#

> + 해당 회차에 모든 번호 일치 시

<img width="598" alt="스크린샷 2021-01-30 오전 12 01 56" src="https://user-images.githubusercontent.com/72774483/106292525-45c0ad80-6290-11eb-99bc-365f428fb329.png">

> + 해당 회차에 5개 번호 + 보너스 번호 일치 시

<img width="597" alt="스크린샷 2021-01-30 오전 12 18 54" src="https://user-images.githubusercontent.com/72774483/106292927-c2ec2280-6290-11eb-8fbb-688739838137.png">

> + 해당 회차에 5개 번호 일치 시

<img width="600" alt="스크린샷 2021-01-30 오전 12 02 15" src="https://user-images.githubusercontent.com/72774483/106293022-dc8d6a00-6290-11eb-9e7a-6771e27b2e49.png">


> + 해당 회차에 4개 번호 일치 시

<img width="597" alt="스크린샷 2021-01-30 오전 12 02 24" src="https://user-images.githubusercontent.com/72774483/106293058-e7e09580-6290-11eb-959f-39fc4dd83f4a.png">

> + 해당 회차에 3개 일치 시

<img width="597" alt="스크린샷 2021-01-30 오전 12 02 43" src="https://user-images.githubusercontent.com/72774483/106293121-f9c23880-6290-11eb-8f9b-3addfa798914.png">

> + 해당 회차에 2개 이하 일치 시

<img width="594" alt="스크린샷 2021-01-30 오전 12 02 54" src="https://user-images.githubusercontent.com/72774483/106293157-05adfa80-6291-11eb-8825-39353f0e098b.png">

2. 로또 번호 비정상 입력
> + 1~45 사이의 수가 아닌 수 입력 시

<img width="595" alt="스크린샷 2021-01-30 오전 12 03 03" src="https://user-images.githubusercontent.com/72774483/106293234-1a8a8e00-6291-11eb-865e-1bcd005190dd.png">

#
Lotto.java
``` java
//로또 번호 범위
if(Integer.parseInt(txt[0].getText())<1||Integer.parseInt(txt[0].getText())>45
        ||Integer.parseInt(txt[1].getText())<1||Integer.parseInt(txt[1].getText())>45
        ||Integer.parseInt(txt[2].getText())<1||Integer.parseInt(txt[2].getText())>45
        ||Integer.parseInt(txt[3].getText())<1||Integer.parseInt(txt[3].getText())>45
        ||Integer.parseInt(txt[4].getText())<1||Integer.parseInt(txt[4].getText())>45
        ||Integer.parseInt(txt[5].getText())<1||Integer.parseInt(txt[5].getText())>45)
{
    turnLabel.setText("로또 번호의 범위는 1~45 입니다.");
    return;
}
```
+ 로또 번호의 범위는 1~45이므로 인덱스 0부터 5까지 모두 조건을 주었습니다.
#


> + 수 이외의 문자 입력 시

<img width="594" alt="스크린샷 2021-01-30 오전 12 03 21" src="https://user-images.githubusercontent.com/72774483/106293291-2fffb800-6291-11eb-8106-f1a26be6101b.png">

#
Lotto.java
``` java
// 로또 번호 창에 숫자 말고 다른 문자 입력했을 떄
for (int i = 0; i < MAX_CNT; i++) {
    if (txt[i].getText().equals("")) { // 로또 번호 쓰지 않고 회차만 적었을 때
        try {
            right[i] = txt[i].getText();
            Integer.parseInt(right[i]);
        } catch (Exception e) {
            turnLabel.setText("");
            return; 
        }
    }
    if (!txt[i].getText().equals("")) { // 로또 번호에 숫자 말고 문자 입력했을 때
        try {
            right[i] = txt[i].getText();
            Integer.parseInt(right[i]);
        } catch (Exception e) {
            turnLabel.setText("숫자만 입력해주세요");
            txt[0].setText("");
            txt[1].setText("");
            txt[2].setText("");
            txt[3].setText("");
            txt[4].setText("");
            txt[5].setText("");
            return;
        }
    }
}
```
+ 공백으로 두거나 숫자가 아닌 다른 문자를 입력했을 때의 조건을 주었습니다.
+ Integer.parseInt를 사용해 숫자가 아니라면 catch로 넘어가게 하였습니다.
#

> + 중복된 번호 입력 시
<img width="594" alt="스크린샷 2021-01-30 오전 12 03 44" src="https://user-images.githubusercontent.com/72774483/106293341-3b52e380-6291-11eb-8577-5e59d519d1f9.png">

#
Lotto.java
``` java
//중복 입력 방지
ArrayList<Integer> checkList = new ArrayList<Integer>();
for(int i = 0; i<txt.length; i++) {
    checkList.add(Integer.parseInt(txt[i].getText()));
}

HashSet<Integer> hash = new HashSet<Integer>(checkList);
ArrayList<Integer> checkedList = new ArrayList<Integer>(hash);
if(checkedList.size()<6) {
    turnLabel.setText("중복 입력된 로또 번호가 있습니다.");
    return;
}
```
+ 배열을 생성하여 해당 배열의 크기가 6보다 작으면 중복 입력된 로또 번호가 있는 것으로 간주하여 중복 입력 방지 기능을 구현하였습니다.
#

---------
## 추후 구현 예정 기능
1. 페이지 이동
2. 랜덤 로또 번호 생성
3. UI 개선
4. 회차 미입력 시 최근 회차와 비교 기능