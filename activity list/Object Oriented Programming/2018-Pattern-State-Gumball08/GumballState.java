/**
 * 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * 상태 패턴
 * GumballState.java
 * 상태 열거형
 * State Driven Transition (상태 기반 전이)
 * 열거형으로 상태 객체들을 정의. 한 자바 파일에 모든 상태 구현.
 * 상태 객체의 메소드가 다음 상태를 반환. 예) public GumballState insertCoin()
 * 상태 객체 간의 coupling tight해지는 단점이 있지만 
 * 열거형으로 구현되기 때문에 이것이 문제되지 않음
 * public void insertCoin(GumballMachine gumballMachine)
 * @author 김상진
 *
 */
public enum GumballState{
	HASCOINSTATE {
		// 마지막 부분에서 this로 return을 하면 이 부분은 필요가 없음
		@Override
		public GumballState insertCoin() {
			System.out.println("이미 동전이 있음");
			return this;
		}

		@Override
		public GumballState ejectCoin() {
			System.out.println("취소되었음");
			return NOCOINSTATE;
		}

		@Override
		public GumballState turnCrank() {
			System.out.println("손잡이를 돌렸음");
			return SOLDSTATE;
		}

		@Override
		public GumballState dispense() {
			System.out.println("손잡이를 돌려야 껌볼이 나옴");
			return this;
		}
	},
	NOCOINSTATE {
		@Override
		public GumballState insertCoin() {
			System.out.println("동전이 삽입되었음");
			return HASCOINSTATE;
		}

		@Override
		public GumballState ejectCoin() {
			System.out.println("반환할 동전 없음");
			return this;
		}

		@Override
		public GumballState turnCrank() {
			System.out.println("동전이 없어 손잡이를 돌릴 수 없음");
			return this;
		}

		@Override
		public GumballState dispense() {
			System.out.println("동전을 투입해야 구입할 수 있음");
			return this;
		}

	},
	SOLDSTATE {
		// 마지막 부분에서 this로 return을 하면 이 부분은 필요가 없음
		@Override
		public GumballState insertCoin() {
			System.out.println("동전을 투입할 수 있는 단계가 아님");
			return this;
		}

		@Override
		public GumballState ejectCoin() {
			System.out.println("반환할 동전이 없음");
			return this;

		}

		@Override
		public GumballState turnCrank() {
			System.out.println("이미 손잡이를 돌렸음");
			return this;
		}

		@Override
		public GumballState dispense() {
			System.out.println("껌볼이 나옴");
			return this;
		}

	},
	SOLDOUTSTATE{
		// 마지막 부분에서 this로 return을 하면 이 부분은 필요가 없음
		@Override
		public GumballState insertCoin() {
			System.out.println("껌볼이 없어 판매가 중단됨");
			return this;
		}

		@Override
		public GumballState ejectCoin() {
			System.out.println("껌볼이 없어 판매가 중단됨");
			return this;
		}

		@Override
		public GumballState turnCrank() {
			System.out.println("껌볼이 없어 판매가 중단됨");
			return this;
		}

		@Override
		public GumballState dispense() {
			System.out.println("껌볼이 없어 판매가 중단됨");
			return this;
		}

	};
	
	public abstract GumballState insertCoin();
	/*
	public abstract GumballState insertCoin() { // 기본 상태로 만든다고 하였을 경우
		return this;
	}
	*/
	public abstract GumballState ejectCoin();
	public abstract GumballState turnCrank();
	public abstract GumballState dispense();
}
