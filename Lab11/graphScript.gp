filename = "timings.dat"
set output "sortingGraph.pdf"
set terminal pdf
set title "Sorting Algorithms Comparative Performance"
set xlabel "Dataset Size"
set ylabel "Time (ms)"
set datafile separator " "
set key left
plot 		filename using 1:2 title "Selection" with lines, \
			filename using 1:4 title "Bubble" with lines, \
			filename using 1:6 title "Insertion" with lines, \
			filename using 1:8 title "Merge" with lines, \
			filename using 1:10 title "Quick" with lines, \
			filename using 1:12 title "Radix" with lines