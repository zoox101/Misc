#CLAND Analysis -- For any corrections please PM u/zoox101
#Author: u/zoox101 
#Data By: u/Gandalfrod

cland = read.table("CLAND.csv", header = TRUE, sep = ",")
table(cland$DROPS)



#Finding P(Boss | Minion Kill)
#PSuccess AND PFailure > 20, can assume normality
#Data for the number of minions killed and bosses spawned
minionskilled = 688; bosseskilled=100
#Probability of spawning a boss for a given minion
pbspawn = bosseskilled/minionskilled
sdbspawn = sqrt(pbspawn * (1-pbspawn) / minionskilled)
#Creating 95% CI
pbspawn; pbspawn + c(1,-1) * qnorm(0.025) * sdbspawn



#Finding P(No Drop | BossKill)
#PSuccess AND PFailure > 20, can assume normality
pnothing = length(subset(cland$HIT, cland$HIT==0))/length(cland$HIT)
sdnothing = sqrt(pnothing * (1-pnothing) / length(cland$HIT))
pnothing; pnothing + c(1,-1) * qnorm(0.025) * sdnothing



#Finding Drop Probabilities
#PSuccess OR PFailure < 20, cannot assume normality
#Assuming CRING, SNAIL, and White Bag drop rates are the same for each boss

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

#Proability of getting a CRING
numcrings = length(subset(cland$DROPS, cland$DROPS=="CRING"))
numcrings/length(cland$DROPS)
highlow(function(x) {mybin(x, successes=numcrings, total=length(cland$DROPS))})

#Probability of getting a SNAIL
numsnails = length(subset(cland$DROPS, cland$DROPS=="SNAIL"))
numsnails/length(cland$DROPS)
highlow(function(x) {mybin(x, successes=numsnails, total=length(cland$DROPS))})

#Probability of getting a UT+
numuts = length(subset(cland$DROPS, cland$DROPS=="ST-RING"))
numuts/length(cland$DROPS)
highlow(function(x) {mybin(x, successes=numuts, total=length(cland$DROPS))})

