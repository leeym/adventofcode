all:
	find * -size 0 -delete
	rm -f */Solution
	rm -f */x
	rm -f */*~
	rm -f */.??*.swp
	rm -f */*.class
	rm -f */*.htm
	rm -f */*.html
	rm -f */.phpunit.result.cache
	rm -fr */out
	git status --porcelain | grep ^\? | awk '{print $$NF}' | xargs git add
	git commit -am "`git status --porcelain | awk '{print $$NF}' | awk -F/ '{print $$1}' | sort -u | xargs`"
	git push
