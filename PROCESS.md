## Process book
## Programmeerproject
### By Levy van der Linde
### Minor Programmeren

*What’s in your fridge?*

#### Day 1
Made a proposal for my app idea. At first I had this idea with personalizing cakes but couldn't find a fitting API for cake recipes. 
After that I started brainstorming and came up with the idea about 'what's in your fridge?'.

#### Day 2
I worked on my design document. This was about thinking about the more technical aspects of my app. 
I thought about how to design the UI's and make sure screens get properly connected. I created all activities with buttons/text etc. 
This was my advanced sketch at day 2:

<img width="500" alt="image" src="https://user-images.githubusercontent.com/47352487/58959822-2e24df00-87a6-11e9-8f9d-98d69b17155f.png">

### Day 3
Thinking about the app being user friendly, I made some changes. 

First of all I decided to try and create one SearchActivity which handles the API search as well as the DB search. 
This way the user doesn't need to search twice but get's both results at once. 

Next to this, I made some name changes in the activities. 
Also there was the GroceryActivity which shows a GroceryList. I thought of creating a function where the user can add a found recipe to their favorites. 
At the first screen it'll be possible to see your favorites so you can remember them locally on your phone to look up in te future.

The app sketch changed to this:

<img width="500" alt="image" src="https://user-images.githubusercontent.com/47352487/58960665-eacb7000-87a7-11e9-9723-5efe525bedaa.png">

After changing the technical aspects I started programming this set up in Android Studio. 

### Day 4
Still working on connecting all the screens and creating buttons etc. Also making sure the Food2Fork API works. 

### Day 5
The API works! The APISearchRequest data is printed containing the name, id and image urls. The next step is to use this for a APIRecipeRequest. Because then the complete recipes will be requested so the ingredients etc too. This is the data that will be shown in the ResultsActivity.

### Day 6
Improving the app by it's ResultsActivity. The names of the recipes already show but the images won't. Working on it.
It turned out the urls for the images were 'http' instead of 'https'. To solve it I splitted the image_url and added a 's' inbetween the 'p' and ':'. Might report it on Food2Forks Github page ;)

Also I decided to do a GridView in the UI from ResultsActivity instead of a Listview.

### Day 7
Working on completing the recipe requests for the alpha version this week. 
The GetRequest works too. The data only had to be put in textviews etc to show details of the recipe in the RecipeActivity. After this i'll start working on using a DB to add users recipes. 

### Day 8
Both requests work now. Now updating RecipeActivity to show a recipe when clicked on it. Also creating the UsersRecipeRequest which makes is possible to request the usersrecipes from a local server. Decided to use rester as a local host because i've used it before and SQlite is local so the users wouldn't be able to search it theirselfs. 

### Day 9
Today and tomorrow i'm gonna work on the users recipe's database. Looking at other apps and their user interface. It can give inspiration as well as looking for points of improvement that I can use in my app. 

### Day 10
At first I wanted to make two search options. One to search for users recipes and one for the online recipes (API). This wouldn't be a nice solution and certainly not very user friendly because it would require a user two search twice but seperately. So i worked on merging the results of the search in the API and database. 

### Day 11
It workes! At first I made it possible to add recipes to the database. After that I wanted the app to show both results at one search. The app shows results from the API as well as the database at once. Still need to implement the filter on the database search. 
Unfortunateley it's not possible to let the user add their own image. That's why user recipes will show with a standard picture so that they are also distinguish from the API recipes.

Lot's of improvement. RecipeActivity is almost done and shows the clicked recipe with it's details. It took some time because it was the first time I used data from a request but not using it in an Adapter. But it works! Also it's possible to click a button that redirects the user to the source_url with the complete recipe.
Used the splitting method from http to https again because I haven't found another solution for it and the TA accepted it like this.

### Day 12
At first I had a really good start. Then I realized I had to make something to make sure the RecipeActivity could recognize whether the user clicked on an UserRecipe or API recipe. I started off quite enthousiasmic but then I messed up and didn't get it back as it was. So I had to go back to my last commit, which was were I left off yesterday. So I created a Recipe tag so it knows to do the right request. Also I created a GetRequest for the user recipes. This is not done yet. The tag does work for API recipes already.
Also have worked on the next and previous button in the ResultsActivity but not done yet. 
Update: thursday night. Both requests work and data is received by RecipeActivity. Difficulty was the difference in format from the user recipes and api recipes. Almost done only the ingredients have to me passed correctly to the RecipeActivity.
I've reached my maximum of API calls, which is a total of 50. So tomorrow i'm gonna work on the next and previous button, invisibility at some point for some buttons, filtering user recipes with searchrequest.

### Day 13

previous en next doen het
Beide soorten recepten kan ej op klikken en krijg je bijbehorend recept
Nog niet filteren op userdatabase 

View recipe online knop is onzichtbaar bij user recipe
Daarvoor in de plaats krijg je bij user recipe juist het recept gewoon al te zien in textview


