# 클린 아키텍쳐 적용
    큰 틀에서 Presentation, Domain, Data 적용. 안드로이드 가이드라인의 클린 아키텍쳐가 아닌
    보편적인 클린 아키텍쳐로 Presentation -> Domain <- Data 의존성으로 구현.

# 모듈 구성
|모듈|설명|
|------|---|
|app|앱의 기본 구성. 앱 레벨에서의 Navigation 등의 UI|
|feature|각 화면에 대한 UI 관련 기능으로 Presentation에 속하는 모듈|
|core:data|Domain의 Repository 구현이나 local, remote관련 datasource와 DTO 구현 등 Data와 관련 된 기능들을 구현|
|core:domain|비지니스 로직(UseCase), Data Model, Repository Interface 등을 포함|
|core:designsystem|규칙화 된 UI 컴포넌트들을 구현하는 모듈|
|core:database|데이터베이스 구현하는 모듈|
|core:network|네트워크 관련 된 내용을 포함하는 모듈|
|core:model|Domain 영역에서 사용 되는 Model|
|core:util|유틸 관련 모듈|

# 구현 내용
* DI -> Hilt
* 이미지 라이브러리 -> Coil
* MVVM을 이용한 화면 구성
* 화면
    1. 검색 리스트
         1. 입력 된 검색어 없을 때 '알콜 성분이 포함 된' api 호출하여 아이템 노출
         2. 입력 된 검색어가 1개 일 때 '시작단어' api 호출하여 아이템 노출
         3. 입력 된 검색어가 1개 초과 일 때 '이름 검색' api 호출하여 아이템 노출
         4. 검색 버튼 없이 검색어가 변경 될 때 마다 검색 처리 - debounce 이용
    2. 아이템 세부화면
    3. 칵테일 영상 및 실시간 스크립트 표기
         1. iframe으로 html 파일 작성해서 assets 폴더에 저장하고 해당 파일 webview로 로드하여 유튜브 영상 노출
         2. html 파일에서 interval 이용하여 현재 재생 시간을 받아오도록 구현
         3. 대본 파일의 Base64 decode가 되지 않아 lexical 파일 바로 사용
         4. 가장 최하위의 단어 data를 flat하게 list로 변경하고 현재 재생시간과 분 단위 범위에 해당하는 단어 data의 select 값을 설정
         5. view에서는 select 값을 이용하여 text의 background 값 변경
         6. 하이라이트 위치가 변경 될 때 해당 position으로 스크롤 되도록 구현
   
