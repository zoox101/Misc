x = runif(100000); y = c()
for(i in 1:100000) y[i] = rbinom(1, size = 100, prob = x[i])
df = data.frame(x, y)
sub = subset(df, df$y == 7)
xgiven7 = sub$x
sorted = sort(xgiven7)
low = trunc(length(sorted) * 0.025)
high = trunc(length(sorted) * 0.975) + 1
sorted[low]; sorted[high]