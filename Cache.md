# Ignite Cache

* mongoDB : Master - Slave Replica Set
  - 오직 하나의 쓰기가 가능한 Primary, Secondary 복제품들은 Primary의 데이터셋을 그대로 반영함
  - Secondary는 Primary 에서 CRUD 작업에 의해 변경되는 사항들을 그대로 반영
  - Database는 비용이 크고 트래픽 증가에 맞춰 유연하게 확장이 불가능
  - 특히 Write 연산이 많은 어플리케이션은 데이터 레이어가 병목 지점이 됨
  
  ===> Cache 도입