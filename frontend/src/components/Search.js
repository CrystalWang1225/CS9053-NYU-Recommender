import React, {Component} from "react";
import { ReactSearchAutocomplete } from 'react-search-autocomplete';
import {movieNames} from '../assets/movieNames';
import Chip from '@material-ui/core/Chip';
// const Search = props => {
//   return (
//     <div className="searchClass">
//       <input
//         className="search__input"
//         type="search"
//         placeholder={props.Placeholder}
//         onChange={props.SearchProps}
//       />
//     </div>
//   );
// };


class Search extends Component {
  constructor(props){
    super(props);
    this.state = {
      isSelected : false,
      activeId: [],
      tags: [
        { id: 1, name: "Apples" },
        { id: 2, name: "Pears" }
      ],
      suggestions: [
        { id: 3, name: "Bananas" },
        { id: 4, name: "Mangos" },
        { id: 5, name: "Lemons" },
        { id: 6, name: "Apricots" }
      ],
      genres:[
        { id: 1, name: "Comedy" },
        { id: 2, name: "Romance" },
        { id: 3, name: "Scifi" },
        { id: 4, name: "Action" },
        { id: 5, name: "Drama" }
      ]
    }


    this.reactTags = React.createRef()
}

onDelete (i) {
  const tags = this.state.tags.slice(0)
  tags.splice(i, 1)
  this.setState({ tags })
}

onAddition (tag) {
  const tags = [].concat(this.state.tags, tag)
  this.setState({ tags })
}

handleOnSearch = (string, results) => {
  console.log(string, results);
};

handleOnHover = (result) => {
  console.log(result);
};

handleOnSelect = (item) => {
  console.log(item);
};

handleOnFocus = () => {
  console.log("Focused");
};

handleOnClear = () => {
  console.log("Cleared");
};

chipFilter = (item, id) => {
  this.setState({activeId: [...this.state.activeId,id]});
  // this.setState({isSelected: true});
}
  render() {
    return (
      <div className="searchClass">
      {/* <input
        className="search__input"
        type="search"
      /> */}
      {/* <ReactTags
        ref={this.reactTags}
        tags={this.state.tags}
        suggestions={this.state.suggestions}
        onDelete={this.onDelete.bind(this)}
        onAddition={this.onAddition.bind(this)} /> */}
         <div style={{ width: 300, margin: 20 }}>
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
          {this.state.genres.map((each, id) => { 
            return (
              
              <Chip
              key={id}
              label={each.name}
              clickable
              color="primary"
              variant={this.state.activeId.includes(id) ? "default":"outlined"}
              onClick={() => this.chipFilter(each, id)}
              style={{"marginRight" : "5px"}}/>
            );}
          )}
          <div style={{ marginTop: 10 }}>Current Genres: ]</div>
          </div>
        </div>
    </div>



    )
  }
}
export default Search;