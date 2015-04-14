filename = "timings.dat"
set output "sortingGraphLog.pdf"
set terminal pdf
set title "Sorting Algorithms Comparative Performance"
set xlabel "Dataset Size"
set ylabel "Time (ms)"
set xrange [0:11000]
set logscale y
set datafile separator " "
set key left
plot 		filename using 1:2 title "Selection" with lines lt 1, \
			filename using 1:2:3 with yerrorbars notitle lt 1, \
			filename using 1:4 title "Bubble" with lines lt 2, \
			filename using 1:4:5 with yerrorbars notitle lt 2, \
			filename using 1:6 title "Insertion" with lines lt 3, \
			filename using 1:6:7 with yerrorbars notitle lt 3, \
			filename using 1:8 title "Merge" with lines lt 4, \
			filename using 1:8:9 with yerrorbars notitle lt 4, \
			filename using 1:10 title "Quick" with lines lt 5, \
			filename using 1:10:11 with yerrorbars notitle lt 5, \
			filename using 1:12 title "Radix" with lines lt 7, \
			filename using 1:12:13 with yerrorbars notitle lt 7