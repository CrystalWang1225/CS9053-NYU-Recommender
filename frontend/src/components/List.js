import React, {Component} from 'react';
import {movieNames} from '../assets/movieNames';
import './List.css';
class List extends Component{
    constructor(props){
        super(props);
        this.state = {

        }
    }

    render() {
        
        let movie = movieNames;
        let anchorLink = "collapseTwo";
        return (
            <div className="container accordion_style">
            <div className="row">
              <div className="col-sm-8 offset-sm-2">
                <div
                  className="accordion md-accordion"
                  id="accordionEx1"
                  role="tablist"
                  aria-multiselectable="true"
                >
                  {movie.map((book, id) => {
                    console.log(id)
                    console.log(anchorLink)
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
                              <p>
                                {" "}
                              </p>
                              {/* <p>{book.volumeInfo.description}</p> */}
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