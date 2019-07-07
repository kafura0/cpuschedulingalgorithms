import java.io.IOException;
import java.lang.management.ManagementFactory;
import com.sun.management.OperatingSystemMXBean;
import java.util.Scanner;
import javax.management.MBeanServerConnection;

class PS{
 public static void main(String args[]) throws IOException
 {
  Scanner sc=new Scanner(System.in);
  MBeanServerConnection mbsc = ManagementFactory.getPlatformMBeanServer();
  OperatingSystemMXBean osMBean = ManagementFactory.newPlatformMXBeanProxy(mbsc, ManagementFactory.OPERATING_SYSTEM_MXBEAN_NAME, OperatingSystemMXBean.class);
  double nanoBefore = System.nanoTime();
  double cpuBefore = osMBean.getProcessCpuTime();
  //System.out.println(cpuBefore+"\n" +nanoBefore);
 System.out.println("Enter the number of processes:"); 
 int n=sc.nextInt();
 int i;
 System.out.println("Enter the burst times:");
 int Pnumb[]=new int[n];
 int burst[]=new int[n];
 int prio[]=new int[n];
 int wait[]=new int[n];
 int turn[]=new int[n];
 int pos = 0;
 int temp;
 //Loop till all burst time is received
 for(i=0;i<n;i++)
 {Pnumb[i]=i+1;
  burst[i]=sc.nextInt();
 }
 System.out.println("Enter priority:");
 //Loop till all priority is received
 for(i=0;i<n;i++) {
  prio[i]=sc.nextInt();}
//Algorithm to sort the input values
for(i=0;i<n;i++){pos=i;
  for(int j=i+1;j<n;j++)
   {
    if(prio[j]<prio[pos])
    pos=j;
   }
   temp=prio[pos];
  prio[pos]=prio[i];
  prio[i]=temp;
 temp=Pnumb[pos];
 Pnumb[pos]=Pnumb[i];
 Pnumb[i]=temp;
 temp=burst[pos];
 burst[pos]=burst[i];
 burst[i]=temp;
 }
wait[0]=0;
for(i=1;i<n;i++)
{
 wait[i]=0;
 for(int j=0;j<i;j++)
 wait[i]+=burst[j];
}
//System.out.println(cpuAfter+"\n" +nanoAfter);
System.out.println("Process\tBurst time\tPriority\tWaiting time\tTurn around time");
//Loop till all output is displayed
for(i=0;i<n;i++)
{
turn[i]=burst[i]+wait[i];
System.out.println(Pnumb[i]+"\t"+burst[i]+"\t\t"+prio[i]+"\t\t"+wait[i]+"\t\t"+turn[i]);
}
float twt = 0;
float tat = 0;
for (i=0;i<n;i++) {
	twt = twt + wait[i];
	tat = tat + turn[i];
}

System.out.println("Average waiting time is:"+twt/n);
System.out.println("Average turnaround time is:"+tat/n);
double cpuAfter = osMBean.getProcessCpuTime();
double nanoAfter = System.nanoTime();

double percent;
if (nanoAfter > nanoBefore)
 percent = ((cpuAfter-cpuBefore)*100L)/
   (nanoAfter-nanoBefore);
else percent = 0;

System.out.println("Cpu usage: "+percent+"%");
}}