# Kingdom [왕국 경영 시뮬레이션]

자바 텀프로젝트 주제를 찾아보던 중, Reigns라는 게임에서 아이디어를 얻어 국가 경영 시뮬레이션을 계획하게 되었다. 
이 게임은 여러 event중에서 적합한 선택지를 골라, 국가를 경영하는 게임인데, 이벤트가 형태는 동일하며, 경우의 수가 많기 때문에 
상속 개념을 잘 활용하여 class로 묶을 수 있고, (후에 제작 과정에서 이벤트의 수가 너무 많아 클래스 상속으로 하면 
비효율적이라고 생각하여 대신 event.txt에 여러 이벤트를 기록해놓고 불러와서 나타내는 형식으로 변경) 
이벤트마다 각각 결과를 선택해줘야 한다는 점에서, 파일 입출력 개념도 충분히 잘 활용할 수 있을 것 같아 팀원들과 상의 후 이 주제를 선정하게 되었다.

# Kingdom (메인 클래스)

사용자가 직접 왕이 되어 여러 가지 선택지를 잘 선택하여 오랜 기간 동안 나라를 통치하는 것이 목표이다. 
매년 년도 값과 나라의 상태를 출력하여, 현재 나라의 정보를 대략적으로 보여준다. 
사용자는 이를 참고하여 뒤에 일어날 이벤트에 각각 적합한 선택지를 선택해야 한다. 매년 Event가 발생하며, 
올바르게 통치를 못할 경우, 나라의 상태 Class 안의 값들이 0이나 100이 될 경우, 게임은 종료되며, 나라의 통치 기간과 더불어, 
다음 게임을 다시 수행 할 것인지 선택지를 출력한다.

