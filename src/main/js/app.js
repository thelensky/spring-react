'use strict';

import ReactDOM from "react-dom";
import React from "react";

const App = () => {
  const changeStateEffect = [];
  const [records, setRecords] = React.useState([]);

  React.useEffect(() => {
    fetch('/record').then(res => res.json()).then(json => setRecords(() => json));
  }, changeStateEffect)

  return (<div>
    <div className={"jumbotron"}>
      <h1 className={"display-5"}>You can left your footprint in history
        here!</h1>
      <hr className="my-4"/>
    </div>
    <Form props={setRecords}/>
    <RecordsList props={records}/>
  </div>)
}

const Form = ({props}) => {
  const [record, setRecord] = React.useState('');
  const [author, setAuthor] = React.useState('');

  const handlerSubmit = (e) => {
    e.preventDefault();

    const datePublish = new Date().toISOString();
    const data = JSON.stringify({author, record, datePublish});

    fetch('/record', {
      method: 'POST',
      headers: {
        "Content-Type": "application/json"
      },
      body: data
    }).then(() => fetch('/record')
      .then(data => data.json())
      .then(json => {
        props(json);
      })).catch(err => console.log(err));
  };

  return (<form className={"mb-3"} onSubmit={handlerSubmit}>
    <div className="form-group">
      <label htmlFor="author">Your Name</label>
      <input className={"form-control"} value={author} onChange={event => {
        setAuthor(event.target.value)
      }}
             type="text" name="author"
             id="author"/>
    </div>
    <div className="form-group">
      <label htmlFor="record">Your Record</label><input
      className={"form-control"} type="text" name="record"
      value={record} onChange={event => {
      setRecord(event.target.value)
    }}
      id="record"/>
    </div>
    <button type="submit" className={"btn btn-primary"}>Make Footprint
    </button>
  </form>);
}

const Record = ({props}) => {

  const {author, record} = props;
  return (<div className={"mb-2"}>
    <div className="card">
      <div className="card-header">
        <div className="card-title">{author}</div>
      </div>
      <div className="card-body">
        <div className="card-text">{record}</div>
      </div>
    </div>
  </div>)
}

const RecordsList = ({props}) => {
  return (props && <>{
    props
      .map(record => <Record key={record.id} props={record}/>)
  }</>)
};


ReactDOM.render(
  <App/>
  ,
  document.getElementById('react')
)
