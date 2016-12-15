x = runif(100000); y = c()
for(i in 1:100000) y[i] = rbinom(1, size = 100, prob = x[i])
df = data.frame(x, y)
sub = subset(df, df$y == 1)
xgiveny = sub$x
sorted = sort(xgiveny)
low = trunc(length(sorted) * 0.025)
high = trunc(length(sorted) * 0.975) + 1
sorted[low]; sorted[high]



highlow(function(x) {mybinhigh(x, successes=1, total=100)})
highlow(function(x) {mybinlow(x, successes=1, total=100)})
