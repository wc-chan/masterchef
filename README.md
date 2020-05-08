# masterchef
Description:

The app retrieves food items from a REST API hosted at endpoints which returns a ````JSON```` array of objects and displays them to users of the app in a ````RecyclerView````. 

Users can select one of the food items to see the recipe and to see additional information about the food item, including a description of the food item, the ingredients required to cook it and the steps required to make it.

In addition to this, users can set preferences for the application to order the list by Breakfast, Lunch or Dinner. These preferences are respected across the application and made persistent using ````SharedPreferences````.

Users can navigate using a ````BottomNavigationView````. 

The application can be rotated, while information on-screen can be maintained after rotation. The layout is functional and appropriate after rotation.
