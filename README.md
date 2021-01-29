# LottoCheck
--------
## Contents
1. 설계의 주안점
2. Using
3. LottoCheck 기능 설명
4. 추후 구현 예정 기능
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
1. 로또 번호, 회차 정상 입력
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

> + 수 이외의 문자 입력 시

<img width="594" alt="스크린샷 2021-01-30 오전 12 03 21" src="https://user-images.githubusercontent.com/72774483/106293291-2fffb800-6291-11eb-8106-f1a26be6101b.png">

> + 중복된 번호 입력 시

<img width="594" alt="스크린샷 2021-01-30 오전 12 03 44" src="https://user-images.githubusercontent.com/72774483/106293341-3b52e380-6291-11eb-8577-5e59d519d1f9.png">

---------
## 추후 구현 예정 기능
1. 페이지 이동
2. 랜덤 로또 번호 생성
3. UI 개선
4. 회차 미입력 시 최근 회차와 비교 기능