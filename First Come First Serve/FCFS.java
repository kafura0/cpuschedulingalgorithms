import java.io.*;

public class FCFS {

	public static void main(String args[]) throws Exception {
		int n, at[], bt[], wt[], tat[];
		float awt = 0, att = 0;
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		System.out.print("Enter number of processes: ");
		n = Integer.parseInt(br.readLine());

		wt = new int[n];
		tat = new int[n];
		bt = new int[n];
		at = new int[n];

		System.out.println("Enter Burst time for each process");
		for(int i = 0; i < n; i++) {
			System.out.println("Process[" + (i+1) + "]");
			bt[i] = Integer.parseInt(br.readLine());
		}

		System.out.println("\nEnter Around Time");
		for(int i = 0; i < n; i++) {
			System.out.println("Process[" + (i+1) + "]");
			at[i] = Integer.parseInt(br.readLine());
		}

		wt[0] = 0;
		for(int i = 1; i < n; i++) {
			wt[i] = wt[i - 1] + bt[i - 1];
			wt[i] = wt[i] - at[i];
		}

		for(int i = 0; i < n; i++) {
			tat[i] = wt[i] + bt[i];
			// Total turnaround time and total waiting time are incremented by the current turnaround time and waiting time respectively.
			att = att + tat[i];
			awt = awt + wt[i];
		}

		System.out.println("\nProcess\t\tBurst Time\tWaiting Time\tTurnaround Time");
		for(int i = 0; i < n; i++) {
			System.out.println((i+1) + "\t\t" + bt[i] + "\t\t" + wt[i] + "\t\t" + tat[i]);
		}

		// Average waiting time and turnaround time are found by calculating the mean number of waiting times and turnaround times respectively.
		awt = awt/n;
		att = att/n;
		System.out.println("\n");
		System.out.println("Average Waiting Time = " + awt);
		System.out.println("Average Turnaround Time = " + att);
	}
}
