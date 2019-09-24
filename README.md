# UrbanDictionary
This app uses the Urban Dictionary API to search for definitions based on user text input. The database is abstracted out by using the Room persistence library. The DefinitionRepository designates a data source as the single source of truth. If the device is online, it saves the web service responses into the database. Changes to the database then trigger callbacks on active LiveData objects, and updates the view. Since the data is cached in the database, which is accessible while offline.

# Next improvements:
- Use Dagger2 for dependency injection to make the classes more loosely coupled
- Use the paging library to allow loading results if the API response is large
- Use data binding to bind data and view more elegantly
- Rotation
