This is a program where we used fork/join to solve the Producer-Consumer problem.

ForkJoinPool is an ExecuterService for ForkJoinTasks. It does something called work-Stealing(If a worker thread has no task in pipeline it will take task from task Queue of another busy thread ,hence workload is efficiently balanced.)