#Will Booker -- HighLow Function

#Returns TRUE if pbinomial(successes, total, x) < alpha
mybin = function(x, successes=0, total=100, alpha=0.05) {
  pbin = pbinom(successes, total, x)
  if(pbin<alpha) return(FALSE); return(TRUE)
}

#Plays a game of higher-lower with the given function
highlow = function(func, min=0, max=1, iter=10000) {
  for(i in 1:iter) {
    x = (min+max)/2
    if(func(x)) {min=x}
    else {max=x}
  }
  return(x)
}

#Returns the p-value for which there is a less than 
#5% chance that a binomial hits a one or lower
highlow(mybin)



