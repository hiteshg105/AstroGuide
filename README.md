# AstroGuide

Screen One:- Display a list of Astro channels (showing at a minimum channel name and
number)
a. Sorting by channel number
b. Sorting by channel name
c. User can mark and tag a favourite channel.


Screen Two: TVGuide in display Grid

a. Display the only current show for all channels currently airing current time, 
if current channel does not air any show currently it will not be shown
i. Sort by channel number or channel name 
ii. Pagination and Lazy loading is done

Assumption:- Channels showing current show have started at max one hour befor current time.
//Can be changed to any time. 

Libraries Used.-
retrofit2
butterknife
dagger2

Architecture- MVP
