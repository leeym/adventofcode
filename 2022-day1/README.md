<!DOCTYPE html>
<html lang="en-us">
<head>
<meta charset="utf-8"/>
<title>Day 1 - Advent of Code 2022</title>
<!--[if lt IE 9]><script src="/static/html5.js"></script><![endif]-->
<link href='//fonts.googleapis.com/css?family=Source+Code+Pro:300&subset=latin,latin-ext' rel='stylesheet' type='text/css'/>
<link rel="stylesheet" type="text/css" href="/static/style.css?30"/>
<link rel="stylesheet alternate" type="text/css" href="/static/highcontrast.css?0" title="High Contrast"/>
<link rel="shortcut icon" href="/favicon.png"/>
<script>window.addEventListener('click', function(e,s,r){if(e.target.nodeName==='CODE'&&e.detail===3){s=window.getSelection();s.removeAllRanges();r=document.createRange();r.selectNodeContents(e.target);s.addRange(r);}});</script>
</head><!--




Oh, hello!  Funny seeing you here.

I appreciate your enthusiasm, but you aren't going to find much down here.
There certainly aren't clues to any of the puzzles.  The best surprises don't
even appear in the source until you unlock them for real.

Please be careful with automated requests; I'm not a massive company, and I can
only take so much traffic.  Please be considerate so that everyone gets to play.

If you're curious about how Advent of Code works, it's running on some custom
Perl code. Other than a few integrations (auth, analytics, social media), I
built the whole thing myself, including the design, animations, prose, and all
of the puzzles.

The puzzles are most of the work; preparing a new calendar and a new set of
puzzles each year takes all of my free time for 4-5 months. A lot of effort
went into building this thing - I hope you're enjoying playing it as much as I
enjoyed making it for you!

If you'd like to hang out, I'm @ericwastl on Twitter.

- Eric Wastl


















































-->
<body>
<header><div><h1 class="title-global"><a href="/">Advent of Code</a></h1><nav><ul><li><a href="/2022/about">[About]</a></li><li><a href="/2022/events">[Events]</a></li><li><a href="https://teespring.com/stores/advent-of-code" target="_blank">[Shop]</a></li><li><a href="/2022/auth/login">[Log In]</a></li></ul></nav></div><div><h1 class="title-event">&nbsp;&nbsp;<span class="title-event-wrap">0.0.0.0:</span><a href="/2022">2022</a><span class="title-event-wrap"></span></h1><nav><ul><li><a href="/2022">[Calendar]</a></li><li><a href="/2022/support">[AoC++]</a></li><li><a href="/2022/sponsors">[Sponsors]</a></li><li><a href="/2022/leaderboard">[Leaderboard]</a></li><li><a href="/2022/stats">[Stats]</a></li></ul></nav></div></header>

<div id="sidebar">
<div id="sponsor"><div class="quiet">Our <a href="/2022/sponsors">sponsors</a> help make Advent of Code possible:</div><div class="sponsor"><a href="https://www.sectorlabs.ro/jobs" target="_blank" onclick="if(ga)ga('send','event','sponsor','sidebar',this.href);" rel="noopener">SECTOR LABS</a> - &quot;Treating employees like adults since 2016&quot;</div></div>
</div><!--/sidebar-->

<main>
<article class="day-desc"><h2>--- Day 1: Calorie Counting ---</h2><p>Santa's reindeer typically eat regular reindeer food, but they need a lot of <a href="/2018/day/25">magical energy</a> to deliver presents on Christmas. For that, their favorite snack is a special type of <em class="star">star</em> fruit that only grows deep in the jungle. The Elves have brought you on their annual expedition to the grove where the fruit grows.</p>
<p>To supply enough magical energy, the expedition needs to retrieve a minimum of <em class="star">fifty stars</em> by December 25th. Although the Elves assure you that the grove has plenty of fruit, you decide to grab any fruit you see along the way, just in case.</p>
<p>Collect stars by solving puzzles.  Two puzzles will be made available on each day in the Advent calendar; the second puzzle is unlocked when you complete the first.  Each puzzle grants <em class="star">one star</em>. Good luck!</p>
<p>The jungle must be too overgrown and difficult to navigate in vehicles or access from the air; the Elves' expedition traditionally goes on foot. As your boats approach land, the Elves begin taking inventory of their supplies. One important consideration is food - in particular, the number of <em>Calories</em> each Elf is carrying (your puzzle input).</p>
<p>The Elves take turns writing down the number of Calories contained by the various meals, snacks, rations, <span title="By &quot;etc&quot;, you're pretty sure they just mean &quot;more snacks&quot;.">etc.</span> that they've brought with them, one item per line. Each Elf separates their own inventory from the previous Elf's inventory (if any) by a blank line.</p>
<p>For example, suppose the Elves finish writing their items' Calories and end up with the following list:</p>
<pre><code>1000
2000
3000

4000

5000
6000

7000
8000
9000

10000
</code></pre>
<p>This list represents the Calories of the food carried by five Elves:</p>
<ul>
<li>The first Elf is carrying food with <code>1000</code>, <code>2000</code>, and <code>3000</code> Calories, a total of <code><em>6000</em></code> Calories.</li>
<li>The second Elf is carrying one food item with <code><em>4000</em></code> Calories.</li>
<li>The third Elf is carrying food with <code>5000</code> and <code>6000</code> Calories, a total of <code><em>11000</em></code> Calories.</li>
<li>The fourth Elf is carrying food with <code>7000</code>, <code>8000</code>, and <code>9000</code> Calories, a total of <code><em>24000</em></code> Calories.</li>
<li>The fifth Elf is carrying one food item with <code><em>10000</em></code> Calories.</li>
</ul>
<p>In case the Elves get hungry and need extra snacks, they need to know which Elf to ask: they'd like to know how many Calories are being carried by the Elf carrying the <em>most</em> Calories. In the example above, this is <em><code>24000</code></em> (carried by the fourth Elf).</p>
<p>Find the Elf carrying the most Calories. <em>How many total Calories is that Elf carrying?</em></p>
</article>
<p>To play, please identify yourself via one of these services:</p>
<p><a href="/auth/github">[GitHub]</a> <a href="/auth/google">[Google]</a> <a href="/auth/twitter">[Twitter]</a> <a href="/auth/reddit">[Reddit]</a> <span class="quiet">- <a href="/about#faq_auth">[How Does Auth Work?]</a></span></p>
</main>

<!-- ga -->
<script>
(function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
(i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
})(window,document,'script','//www.google-analytics.com/analytics.js','ga');
ga('create', 'UA-69522494-1', 'auto');
ga('set', 'anonymizeIp', true);
ga('send', 'pageview');
</script>
<!-- /ga -->
</body>
</html>
https://adventofcode.com/2022/day/1