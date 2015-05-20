package polly;

public class Timer {
	double startSystemTimeNanoseconds = 0;
	double stopSystemTimeNanoseconds = 0;
	
	public void startTimer(){
		startSystemTimeNanoseconds = System.nanoTime();
	}
	public double stopTimer(){
		stopSystemTimeNanoseconds = System.nanoTime();
		return getTimeElapsedNanoseconds();
	}
	public double getTimeElapsedNanoseconds() {
		return stopSystemTimeNanoseconds - startSystemTimeNanoseconds;
	}
	public double getTimeElapsedMilliseconds(){
		return getTimeElapsedNanoseconds()/1000;
	}
	public void resetTimer(){
		startSystemTimeNanoseconds = 0;
		stopSystemTimeNanoseconds = 0;
	}
	public Timer(){
		startTimer();
	}
}
