#Will Booker -- HighLow Function

#Returns high-low for the low tail being >= val
myqbin = function(x, val=1.5) {
  qbin = qbinom(0.025, 100, x) - val
  return(min(max(qbin,0),1))
}

#Plays a game of higher-lower with the given function
highlow = function(func, min=0, max=1, iter=10000) {
  for(i in 1:iter) {
    x = (min+max)/2
    if(func(x) == 0) {min=x}
    else {max=x}
  }
  return(x)
}

#Returns the p-value for which there is a less than 
#5% chance that a binomial hits a one or lower
highlow(myqbin)



