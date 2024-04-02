In this Android project, I've implemented the MVVM architectural pattern to display a list of albums fetched from a JSON API. 

- The `MainActivity` displays the list using a RecyclerView and fetches data using Volley.
- Clicking on an album thumbnail opens a `LargerImageActivity` showing the full-size image using Glide.
- The `AlbumViewModel` acts as a mediator between the View and the data layer, fetching data from the repository.
- Data persistence is ensured through Room Persistence Library, storing album data locally.
- The project targets API 21 and above, utilizing popular libraries like Volley and Glide.
- Repository logic is encapsulated in the `AlbumRepository`, handling data retrieval from both network and database sources.
