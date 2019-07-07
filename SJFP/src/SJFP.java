import java.io.IOException;
import java.lang.management.ManagementFactory;
import com.sun.management.OperatingSystemMXBean;
import java.util.Scanner;
import javax.management.MBeanServerConnection;

public class SJFP {
 public static void main(String args[]) throws IOException
 {
      int n;
      Scanner sc=new Scanner(System.in);
      //Cpu Usage code
      MBeanServerConnection mbsc = ManagementFactory.getPlatformMBeanServer();
      OperatingSystemMXBean osMBean = ManagementFactory.newPlatformMXBeanProxy(mbsc, ManagementFactory.OPERATING_SYSTEM_MXBEAN_NAME, OperatingSystemMXBean.class);
      double nanoBefore = System.nanoTime();
      double cpuBefore = osMBean.getProcessCpuTime();
      System.out.println("Please enter the number of Processes: ");
       n = sc.nextInt();
       int process[][] = new int[n + 1][5];
       for(int i = 1; i <= n; i++)
       {
      System.out.println("Please enter the Arrival Time for Process " + i + ": ");
      process[i][0] = sc.nextInt();
      System.out.println("Please enter the Burst Time for Process " + i + ": ");
      process[i][1] = sc.nextInt();
      process[i][4] = process [i][1];
     }
       System.out.println();
     
       //Calculation of Turnaround Time\
     int total_time = 0;
     for(int i = 1; i <= n; i++)
     {
      total_time += process[i][1];
     }
     for(int i = 0; i < total_time; i++)
     {
      //Choose shortest available process
      int sel_proc = 0;
      int min = 99999;
      for(int j = 1; j <= n; j++)
      {
       if(process[j][0] <= i)//Check if Process has arrived
       {
        if(process[j][1] < min && process[j][1] != 0)
        {
         min = process[j][1];
         sel_proc = j;
        }
       }
      }
      //Reduce remaining time of selected process
      process[sel_proc][1]--;
      
      //Waiting Time and Turnaround Time Calculation
      for(int j = 1; j <= n; j++)
      {
       if(process[j][0] <= i)
       {
        if(process[j][1] != 0)
        {
         process[j][3]++;//If process arrived but incomplete Turnaround +1
            if(j != sel_proc)//If process arrived but not assigned Waiting +1
             process[j][2]++;
        }
        else if(j == sel_proc)//If process is assigned and was completed
         process[j][3]++;
       }
      }
     }
     System.out.println();
     System.out.println();
     
     //Printing the WT and TT for each Process
     System.out.println("Process\tBurst time\tArrival time\tWaiting time\tTurnaround time ");
     for(int i = 1; i <= n; i++)
     {
      System.out.printf("%d\t%2d\t\t%2d\t\t%2d\t\t%2d",i,process[i][4],process[i][0],process[i][2],process[i][3]);
      System.out.println();
     }
     
     System.out.println();
     
     //Printing the average Waiting Time & Turnaround Time
     float WT = 0,TT = 0;
     for(int i = 1; i <= n; i++)
     {
      WT += process[i][2];
      TT += process[i][3];
     }
     System.out.println("The Average Waiting Time is:"+WT/n);
     System.out.println("The Average Turnaround Time is:"+TT/n );
     double cpuAfter = osMBean.getProcessCpuTime();
     double nanoAfter = System.nanoTime();

     double percent;
     if (nanoAfter > nanoBefore)
      percent = ((cpuAfter-cpuBefore)*100L)/
        (nanoAfter-nanoBefore);
     else percent = 0;

     System.out.println("Cpu usage: "+percent+"%");
 }
    
}