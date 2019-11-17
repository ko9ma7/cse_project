
public enum DoorState {
	OPENED{
		@Override
		public DoorState open() {
			System.out.println("이미 열려 있음");
			return this;
		}

		@Override
		public DoorState close() {
			System.out.println("문이 닫힘");
			return CLOSED;
		}

		@Override
		public DoorState lock() {
			System.out.println("문이 열려 있어 잠글 수 없음");
			return this;
		}

		@Override
		public DoorState unlock() {
			System.out.println("문이 잠겨 있지 않음");
			return this;
		}
	},
	CLOSED{
		@Override
		public DoorState open() {
			System.out.println("문이 열림");
			return OPENED;
		}

		@Override
		public DoorState close() {
			System.out.println("문이 이미 닫혀 있음");
			return this;
		}

		@Override
		public DoorState lock() {
			System.out.println("문을 잠금");
			return LOCKED;
		}

		@Override
		public DoorState unlock() {
			System.out.println("문이 잠겨 있지 않음");
			return this;
		}
	},
	LOCKED{
		@Override
		public DoorState open() {
			System.out.println("문이 잠겨 있어 열 수 없음");
			return this;
		}

		@Override
		public DoorState close() {
			System.out.println("문이 이미 닫혀 있음");
			return this;
		}

		@Override
		public DoorState lock() {
			System.out.println("문이 이미 잠겨 있음");
			return this;
		}

		@Override
		public DoorState unlock() {
			System.out.println("문의 잠금을 해제함");
			return CLOSED;
		}
	};
	public abstract DoorState open();
	public abstract DoorState close();
	public abstract DoorState lock();
	public abstract DoorState unlock();
}
