import React, {Component} from 'react';
import {movieNames} from '../assets/movieNames';
import './List.css';
import Favorite from '@material-ui/icons/Favorite';
import FavoriteBorder from '@material-ui/icons/FavoriteBorder';
import FormControlLabel from '@material-ui/core/FormControlLabel';
import Checkbox from '@material-ui/core/Checkbox';
import { ReactSearchAutocomplete } from 'react-search-autocomplete';
import Chip from '@material-ui/core/Chip';
import Button from '@material-ui/core/Button';

class List extends Component{
    constructor(props){
        super(props);
        this.state = {
          genres:[
            { id: 1, name: "Comedy" },
            { id: 2, name: "Romance" },
            { id: 3, name: "Scifi" },
            { id: 4, name: "Action" },
            { id: 5, name: "Drama" }
          ],
          activeId: [],
          movieList: movieNames,
          currentGenre:[],
          likedList:[],
          isLiked: false,
          currMovieList:[]
        }
        this.displayLiked = this.displayLiked.bind(this);
    }

    componentDidMount(){
        // this.getData();
    }

    componentDidUpdate(prevProps, prevState){
      if (prevState.activeId !== this.state.activeId){
        this.filterGenre();
        }

      }

    
    filterGenre() {
      let current = [];
      for (let i = 0; i < this.state.activeId.length; i++){
        let test = this.state.genres.find ((item) => {
          return item.id === this.state.activeId[i]
        }) 
        current=[...current, test];
    }
    this.setState({currentGenre: current})
  }

    async getData() {
        await fetch(
            `http://localhost:8080/scrape`,
            {
                method:'GET',
                mode:'cors'
            }).then(response => response.json()).then(json => {

                console.log(json)
            }).catch(console.error);
            }
    
            onDelete (item) {
              let arr = this.state.activeId
              for (let i = 0; i < this.state.activeId.length; i++) {
                if (this.state.activeId[i] === item.id) arr.splice(i, 1);
              }
              this.setState({activeId: arr});
              this.filterGenre();
            }
            
            onAddition (tag) {
              const tags = [].concat(this.state.tags, tag)
              this.setState({ tags })
            }
            
            handleOnSearch = (string, results) => {
              //console.log(string, results);
            };
            
            handleOnHover = (result) => {
              //console.log(result);
            };
            
            handleOnSelect = (item) => {
              let existed = false;
              for (let movie of this.state.currMovieList){
                if (movie.name === item.name){
                  existed = true;
                }
              }
              if (!existed){
                this.setState({currMovieList: [...this.state.currMovieList, item]});
              }
            };
            
            handleOnFocus = () => {
              //console.log("Focused");
            };
            
            handleOnClear = () => {
              //console.log("Cleared");
            };
            
            chipFilter = (item) => {
              console.log(this.state.activeId)
              if (!this.state.activeId.includes(item.id)){
                this.setState({activeId: [...this.state.activeId,item.id]});
              }
             
              // this.setState({isSelected: true});
            }

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

            displayLiked() {
              this.setState({isLiked: !this.state.isLiked})
            }

            titleDelete = (each) => {
              let currentList = this.state.currMovieList;
              for (let i = 0; i < this.state.currMovieList.length; i++){
                if (this.state.currMovieList[i].name === each.name){
                  currentList.splice(i,1);
                }
            }
            this.setState({currMovieList: currentList});
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
                            items={movieNames}
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
                                  <b>Cast:</b> {book.cast}
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
                              <div className="col-sm-4">
                                <img className="images"
                                  // onError={this.addDefaultSrc}
                                  src={
                                    book.image !== undefined
                                      ? book.image
                                      : ""
                                  }
                                  alt=""
                                />
                              </div>
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