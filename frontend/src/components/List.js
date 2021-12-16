import React, {Component} from 'react';
import './List.css';
import Favorite from '@material-ui/icons/Favorite';
import FavoriteBorder from '@material-ui/icons/FavoriteBorder';
import FormControlLabel from '@material-ui/core/FormControlLabel';
import Checkbox from '@material-ui/core/Checkbox';
import { ReactSearchAutocomplete } from 'react-search-autocomplete';
import Chip from '@material-ui/core/Chip';
import Button from '@material-ui/core/Button';
import Slider from '@material-ui/core/Slider';

class List extends Component{
    constructor(props){
        super(props);
        this.state = {
          // Genres this project has been worked for so far. Can extend this to more genres
          genres:[
            { id: 1, name: "Comedy" },
            { id: 2, name: "Scifi" },
            { id: 3, name: "Horror" },
            { id: 4, name: "Romance" },
            { id: 5, name: "Action" },
            { id: 6, name: "Thriller" },
            { id: 7, name: "Drama" },
            { id: 8, name: "Mystery" },
            { id: 9, name: "Crime" },
          ],
          activeId: [],
          movieList:[],
          currentGenre:[],
          likedList:[],
          isLiked: false,
          currMovieList:[],
          isReset: false,
          range:[6.4, 8.9]
        }
        this.displayLiked = this.displayLiked.bind(this);
        this.resetFilter = this.resetFilter.bind(this);
        this.getRange = this.getRange.bind(this);
        this.chipFilter = this.chipFilter.bind(this);
    }

    componentDidMount(){
        this.getAll();
    }

    componentDidUpdate(prevProps, prevState){
      if (prevState.activeId !== this.state.activeId && this.state.isReset !== true){
        this.filterGenre("add");
        }
      }

    
    filterGenre(action) {
      //If all the genre tags are deleted, reset the filtering list
      if (action === "delete" && this.state.activeId.length === 0){
        this.resetFilter()
        return;
      }
      let current = [];
      for (let i = 0; i < this.state.activeId.length; i++){
        let test = this.state.genres.find ((item) => {
          return item.id === this.state.activeId[i]
        }) 
        current=[...current, test];
    }
    this.setState({currentGenre: current})
    this.getDataGenre(current, action);
  }
  
  /* This function filters movie based on the selected gneres or rating. The displayed result will only display the intersection of
  both functions result.
  */
  filterMovie(id, type) {
    let prevList = this.state.allList;
    if (type === "genre"){
      prevList = this.state.prevListGenre
    }
    if (type === "range"){
      prevList = this.state.prevListRange
    }

    let current = [];
    for (let i = 0; i < id.length; i++){
      let test = prevList.find ((item) => {
        return item.id === id[i]
      })
      if (test !== undefined){
        current=[...current, test];
      }
  }
    this.setState({movieList: current})
    if (type === "genre"){
      this.setState({prevListRange: current})
    }
    if (type === "range"){
      this.setState({prevListGenre: current})
    }

  }

  //this function retrives all the movies from the database as display as the default option
  async getAll() {
    await fetch(
        `http://localhost:8080/getAll` ,
        {
            method:'GET',
            mode:'cors'
        }).then(response => response.json()).then(json => {
            let movies = this.getGenreMapping(json);
            this.setState({movieList: movies})
            this.setState({allList: movies})
            this.setState({prevListGenre: movies})
            this.setState({prevListRange: movies})
        }).catch(console.error);
        }
    
        getDataName = (item) => {
          let cur = this.state.movieList
          let index = -1
          for (let i = 0; i < cur.length; i++){
            if (cur[i].id === item.id){
              index = i;
            }
          } 
          if (index !== -1){
            let temp = cur[0]
            cur[0] = item
            cur[index] = temp
          } else {
            let temp = cur[0]
            cur[0] = item
            cur[cur.length] = temp
          }
          this.setState({movieList: cur})
          }
    
    //The async function uses the API to find the list of movies within the range of the ratings from the slider
    async getDataRange() {
        await fetch(
            `http://localhost:8080/search?min=` + this.state.range[0] + '&max=' + this.state.range[1] ,
            {
                method:'GET',
                mode:'cors'
            }).then(response => response.json()).then(json => {

              let filteredID = [];
              for (let i = 0; i< json.length; i++){
                filteredID = [...filteredID,json[i].id]
              }
              this.filterMovie(filteredID,"range");
            }).catch(console.error);
            }
            
            getGenreMapping (json){
              for (let i = 0; i < json.length; i++){
                let current = json[i];
                let currGenre = [];
                if (current.is_action){
                  currGenre = [...currGenre, "Action"]
                }
                if (current.is_comedy){
                  currGenre = [...currGenre, "Comedy"]
                }
                if (current.is_horror){
                  currGenre = [...currGenre, "Horror"]
                }
                if (current.is_scifi){
                  currGenre = [...currGenre, "Scifi"]
                }
                if (current.is_romance){
                  currGenre = [...currGenre, "Romance"]
                }
                if (current.is_thriller){
                  currGenre = [...currGenre, "Thriller"]
                }
                if (current.is_drama){
                  currGenre = [...currGenre, "Drama"]
                }
                if (current.is_mystery){
                  currGenre = [...currGenre, "Mystery"]
                }
                if (current.is_crime){
                  currGenre = [...currGenre, "Crime"]
                }
                json[i].genre = currGenre;
              }

              return json
            }
            
            //The async function uses the API to find the list of movies based on the genres
            //Note that one movie might belong to different genres, the function also makes sure that there won't be more duplciates in the web page
            async getDataGenre(data,action) {
              if (action === "delete" && data.length === 0){
                // this.resetFilter();
                this.setState({movieList: this.state.prevListRange})
                return;
              }

                let filteredID = [];
                for (let i = 0; i < data.length; i++){
                  let current = data[i];
                  await fetch(
                    `http://localhost:8080/search/`+ current.name.toLowerCase(),
                    {
                        method:'GET',
                        mode:'cors'
                    }).then(response => response.json()).then(json => {
                        for (let i = 0; i < json.length; i++){
                          filteredID.push(json[i].id)
                        }
                    }).catch(console.error);
                    }
                    
                    this.filterMovie(filteredID,"genre");
              }
            
            //this function deals with the deletion of selected genre 
            onDelete (item) {
              let arr = this.state.activeId
              for (let i = 0; i < this.state.activeId.length; i++) {
                if (this.state.activeId[i] === item.id) arr.splice(i, 1);
              }
              this.setState({activeId: arr});
              this.filterGenre("delete");
            }
            // The empty functions are placeholder for autocomplete search component to give the autocomplete bar
            //more power to interact with the searched/unsearched results
            handleOnSearch = (string, results) => {
            };
            
            handleOnHover = (result) => {
            };

            handleOnFocus = () => {
              //console.log("Focused");
            };
            
            handleOnClear = () => {
              //console.log("Cleared");
            };
            
            
            // This function handles the autocomplete result from the search bar, it also takes care of the duplictes
            handleOnSelect = (item) => {
              let existed = false;
              for (let movie of this.state.currMovieList){
                if (movie.name === item.name){
                  existed = true;
                }
              }
              //if not existed, using the searchByName API to return the movie of selected to the front
              if (!existed){
                this.setState({currMovieList: [...this.state.currMovieList, item]});
                this.getDataName(item);
              }

              
            };
            
            //this function will be fired to add selected Genres
            chipFilter(item) {
              this.setState({isReset: false})
              if (!this.state.activeId.includes(item.id)){
                this.setState({activeId: [...this.state.activeId,item.id]});
              }
            }

            //this function creates and updates the liked watchlist for each session
            handleLiked = (movie) => (event) => {

              if (event.target.checked === true){
                movie.checked = true;
                if (!this.state.likedList.includes(movie)){
                  this.setState({likedList: [...this.state.likedList, movie]});
                }
              } else if (event.target.checked === false){
                movie.checked = false;
                let currentList = this.state.likedList;
                for (let i = 0; i < this.state.likedList.length; i++){
                  if (this.state.likedList[i] === movie){
                    currentList.splice(i,1);
                  }
                  this.setState({likedList: currentList});
                }
              }
            }

            // to display the wathlist or the overall list
            displayLiked() {
              this.setState({isLiked: !this.state.isLiked})
            }

            //this function will fire to reset all the filtering options
            resetFilter() {
              this.setState({isReset: true})
              this.setState({activeId: []})
              let current = this.state.allList
              this.setState({activeId: []})
              this.setState({currMovieList:[]})
              this.setState({currentGenre:[]})
              this.setState({movieList: current})
              this.setState({range:[6.4, 8.9]})             
              this.setState({prevListRange: this.state.allList})
              this.setState({prevListGenre: this.state.allList})


            }

            //this title helps delete the selected titles
            titleDelete = (each) => {
              let currentList = this.state.currMovieList;
              for (let i = 0; i < this.state.currMovieList.length; i++){
                if (this.state.currMovieList[i].name === each.name){
                  currentList.splice(i,1);
                }
            }
            this.setState({currMovieList: currentList});
          }

          //this function updates the range of the range and based on the range to retrive data from the backend API
          getRange = (event, value) => {
            this.setState({range: value});
            this.getDataRange();
          }

    render() {
        let movie= []
        if (this.state.isLiked === false){
          movie = this.state.movieList;
        } else {
          movie = this.state.likedList;
        }
        let anchorLink = "collapseTwo";
        return (
            <div className="container accordion_style">
            <div className="row">
              <div className="col-sm-8 offset-sm-2">
              <Button color="primary" 
              variant={this.state.isLiked ? "contained": "outlined"} 
              onClick={this.displayLiked}>See My Watchlist</Button>
              {this.state.isLiked ? null :
                            <div style={{ width:800, margin: 10 }}> 
                            <div style={{ marginBottom: 10 }}>Search for a Movie here</div> 
                            <ReactSearchAutocomplete
                            className="search__input"
                            items={this.state.allList}
                            onSearch={this.handleOnSearch}
                            onHover={this.handleOnHover}
                            onSelect={this.handleOnSelect}
                            onFocus={this.handleOnFocus}
                            onClear={this.handleOnClear}
                            styling={{ zIndex: 2, position: 'absolute' }}
                            autoFocus
                        /> 
                        <div className="tag">
                        {this.state.genres.map((each) => { 
                          return (
                            
                            <Chip
                            key={each.id}
                            label={each.name}
                            clickable
                            color="primary"
                            variant={this.state.activeId.includes(each.id) ? "default":"outlined"}
                            onClick={() => this.chipFilter(each)}
                            onDelete={() => this.onDelete(each)}
                            style={{"marginRight" : "5px", "marginTop" : "5px"}}/>
                          );}
                        )}
                        <div style={{ marginTop: 10, marginBottom:10 }}>Selected Genres: 
                        {this.state.currentGenre.map((each) => { 
                          return (              
                            <Chip
                            key={each.id}
                            label={each.name}
                            color="primary"
                            variant="outlined"
                            style={{"marginRight" : "5px"}}/>
                          );}
                        )}
                        </div>
                        <div style={{ marginTop: 10, marginBottom:10 }}>Selected Movie Titles: 
                        {this.state.currMovieList.map((each, id) => { 
                          return (              
                            <Chip
                            key={id}
                            label={each.name}
                            onDelete={() => this.titleDelete(each)}
                            color="primary"
                            variant="outlined"
                            style={{"marginRight" : "5px"}}/>
                          );}
                        )}
                        </div>

                        </div>
                      <div style={{ marginTop: 10, marginBottom:10 }}>Search by rating:  </div>
                                            <Slider
                              getAriaLabel={() => "Test"}
                              value={this.state.range}
                              // getAriaValueText={}s
                              onChangeCommitted={this.getRange}
                              valueLabelDisplay="auto"
                              step={0.1}
                              marks
                              min={2.0}
                              max={9.5}
                            />
                      <Button color="primary" 
                    variant="outlined" 
                     onClick={this.resetFilter}>Reset Filters</Button>
                      </div>
              }

                <div
                  className="accordion md-accordion"
                  id="accordionEx1"
                  role="tablist"
                  aria-multiselectable="true"
                >
                  {movie.map((book, id) => {
                    return (
                      <div key={id} className="card card_style">
                        <div className="card-header" role="tab" id="headingTwo1">
                          <a
                            className="collapsed"
                            data-toggle="collapse"
                            data-parent="#accordionEx1"
                            href={`#${anchorLink}${id}`}
                            aria-expanded="false"
                            aria-controls={`#${anchorLink}${id}`}
                          >
                            <h5 className="mb-0 title_style">
                              {book.name}
                              <i className="fas fa-angle-down rotate-icon" />
                            </h5>
                          </a>
                        </div>
    
                        <div
                          id={`${anchorLink}${id}`}
                          className="collapse"
                          role="tabpanel"
                          aria-labelledby="headingTwo1"
                          data-parent="#accordionEx1"
                        >
                          <div className="card-body">
                            <div className="row row_info">
                              <div className="col-sm-8">
                                <p>
                                  <b>Name:</b> {book.name}
                                </p>
                                <p>
                                  <b>Rating:</b> {book.rating}
                                </p>
                                <p>
                                <FormControlLabel
                                control={
                            <Checkbox icon={<FavoriteBorder />}
                            checked = {book.checked === undefined ? false : book.checked} 
                            checkedIcon={<Favorite />} 
                            onChange={this.handleLiked(book)}
                            />
                                }
                          label="Like this movie"
                            />
                                </p>
                              </div>
                              {/* <-----  Uncomment this if there is image information in the database to diplay*/}
                              {/* <div className="col-sm-4">
                                <img className="images"
                                  // onError={this.addDefaultSrc}
                                  src={
                                    book.image !== undefined
                                      ? book.image
                                      : ""
                                  }
                                  alt=""
                                />
                              </div> */}
                            </div>
                          
                            <div className="description_book">
                              {book.genre.map((each,id) => { 
                              return (              
                                <Chip
                                key={id}
                                label={each}
                                color="primary"
                                variant="outlined"
                                style={{"marginRight" : "5px"}}/>
                              );}
                            )}
                            </div>
                          </div>
                        </div>
                      </div>
                    );
                  })}
                </div>
              </div>
            </div>
          </div>    
        )
    }
}

export default List;