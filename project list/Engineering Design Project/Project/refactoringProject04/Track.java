package refactoringProject04;

public class Track {
	private String titleImage; // 게임 시작 전 앨범 이름 이미지
	private String startImage; // 게임 시작 전 앨범 이미지
	private String gameImage;  // 게임 시작 배경 이미지
	private String startMusic; // 곡 선택 전에 미리 듣기
	private String gameMusic;  // 곡 선택 후 게임 시작 배경 곡
	private String titleName;  // 곡의 제목
	
	// 곡 제목을 배경 음악에서 String만 추출하자
	public Track(String titleImage, String startImage, String gameImage, String startMusic, String gameMusic, String titleName) {
		super();
		this.titleImage = titleImage;
		this.startImage = startImage;
		this.gameImage = gameImage;
		this.startMusic = startMusic;
		this.gameMusic = gameMusic;
		this.titleName = titleName;
	}
	public String getTitleImage() {
		return titleImage;
	}
	public void setTitleImage(String titleImage) {
		this.titleImage = titleImage;
	}
	public String getStartImage() {
		return startImage;
	}
	public void setStartImage(String startImage) {
		this.startImage = startImage;
	}
	public String getGameImage() {
		return gameImage;
	}
	public void setGameImage(String gameImage) {
		this.gameImage = gameImage;
	}
	public String getStartMusic() {
		return startMusic;
	}
	public void setStartMusic(String startMusic) {
		this.startMusic = startMusic;
	}
	public String getGameMusic() {
		return gameMusic;
	}
	public void setGameMusic(String gameMusic) {
		this.gameMusic = gameMusic;
	}
	public String getTitleName() {
		return titleName;
	}
	public void setTitleName(String titleName) {
		this.titleName = titleName;
	}
}
