import java.util.*;

class Process {
    String name;
    int burstTime;
    int remainingTime;

    public Process(String name, int burstTime) {
        this.name = name;
        this.burstTime = burstTime;
        this.remainingTime = burstTime;
    }
}

public class RoundRobinScheduling {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of processes: ");
        int numProcesses = scanner.nextInt();

        System.out.print("Enter the time quantum: ");
        int timeQuantum = scanner.nextInt();

        List<Process> processes = new ArrayList<>();
        Queue<Process> queue = new LinkedList<>();

        for (int i = 1; i <= numProcesses; i++) {
            System.out.print("Enter burst time for Process P" + i + ": ");
            int burstTime = scanner.nextInt();
            processes.add(new Process("P" + i, burstTime));
        }

        int currentTime = 0;

        while (!processes.isEmpty()) {
            Process currentProcess = processes.remove(0);
            int executionTime = Math.min(currentProcess.remainingTime, timeQuantum);

            System.out.println("Executing " + currentProcess.name + " for " + executionTime + " units");

            currentProcess.remainingTime -= executionTime;
            currentTime += executionTime;

            if (currentProcess.remainingTime > 0) {
                queue.add(currentProcess);
            }

            while (!queue.isEmpty() && queue.peek().remainingTime <= 0) {
                Process completedProcess = queue.poll();
                System.out.println(completedProcess.name + " has completed.");
            }

            if (!processes.isEmpty()) {
                queue.add(currentProcess);
            }
        }

        System.out.println("All processes have completed.");
        scanner.close();
    }
}
