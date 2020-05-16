'use strict';

import React from "react";
import ReactDOM from 'react-dom';

const App = () => {
	const changeStateEffect = [];
	const [records, setRecords] = React.useState([]);
	const [isLogged, setIsLogged] = React.useState(false);

	React.useEffect(() => {
		fetch('/record')
			.then(resData => {
				return resData.json();
			})
			.then(json => {
				setRecords(() => json !== null ? json : [])
			})
			.catch(err => console.log(`fetch Error >>> ${err}`));
	}, changeStateEffect);

	const handleDelete = id => {
		fetch(`/delete/${id}`, { method: "DELETE" })
			.then(res => {
				if (res.ok) {
					const newRecords = records.filter(record => {
						return record.id !== id;
					});
					setRecords(newRecords);
				}
			})
	};

	return (<div>
		<div className={"jumbotron"}>
			<h1 className={"display-5"}>You can left your footprint in history
        here!</h1>
			<hr className="my-4" />
		</div>
		{isLogged ? <Logout setIsLogged={setIsLogged} /> :
			<Login loginHandler={setIsLogged} />}
		<Form props={setRecords} />
		<RecordsList props={{ isLogged, records, handleDelete }} />
	</div>)
};

const Login = (props) => {
	// console.log(props.loginHandler);

	const [user, setUser] = React.useState('');
	const [password, setPassword] = React.useState('');
	const [msgWrong, setMsgWrong] = React.useState(false);
	const handleLogin = e => {
		e.preventDefault();
		fetch('/login_process', {
			method: 'POST',
			headers: {
				"Content-Type": "application/x-www-form-urlencoded"
			},
			body: `username=${user}&password=${password}`
		})
			.then(res => {
				if (!res.url.endsWith("error")) {
					setUser('');
					setPassword('');
					setMsgWrong(false);
					props.loginHandler(true);
				} else {
					setMsgWrong(true);
				}
			})


	};
	return (<form className="my-3" onSubmit={handleLogin}>
		<div className="row">
			<div className="col">
				<input type="text" className="form-control" value={user}
					onChange={event => { setUser(event.target.value); }}
					placeholder="Username" />
			</div>
			<div className="col">
				<input type="password" className="form-control" value={password}
					onChange={event => { setPassword(event.target.value); }}
					placeholder="Password" autoComplete="on" />
			</div>
			<div className="col-2">
				<button className="btn btn-primary" type="submit">Login</button>
			</div>
		</div>
		{msgWrong && (<div className="alert alert-primary my-2" role="alert">
			Wrong login or password!
		</div>)}
	</form>);
};

const Logout = (props) => {
	const handleLogout = event => {
		event.preventDefault();
		fetch('/logout')
			.then(res => {
				if (res.ok) {
					props.setIsLogged(false)
				}
			})
			.catch(err => console.log(err));
	};
	return (<div className="my-3">
		<button className="btn btn-primary" onClick={handleLogout}>Logout</button>
	</div>);
};

const Form = ({ props }) => {
	const [record, setRecord] = React.useState('');
	const [author, setAuthor] = React.useState('');

	const handlerSubmit = (e) => {
		e.preventDefault();

		const datePublish = new Date().toISOString();
		const data = JSON.stringify({ author, record, datePublish });

		fetch('/record', {
			method: 'POST',
			headers: {
				"Content-Type": "application/json"
			},
			body: data
		})
			.then(() => fetch('/record')
				.then(res => res.json())
				.then(json => {
					props(json);
				}))
			.catch(err => console.log(err));
	};

	return (<form className={"mb-3"} onSubmit={handlerSubmit}>
		<div className="form-group">
			<label htmlFor="author">Your Name</label>
			<input className={"form-control"} value={author} onChange={event => {
				setAuthor(event.target.value)
			}}
				type="text" name="author"
				id="author" />
		</div>
		<div className="form-group">
			<label htmlFor="record">Your Record</label><input
				className={"form-control"} type="text" name="record"
				value={record} onChange={event => {
					setRecord(event.target.value)
				}}
				id="record" />
		</div>
		<button type="submit" className={"btn btn-primary"}>Make Footprint
    </button>
	</form>);
};

const Record = ({ props }) => {
	const { author, record, isLogged, id, handleDelete } = props;

	return (<div className={"mb-2"}>
		<div className="card">
			<div className="card-header d-flex flex-row">
				<div className="card-title">{author}</div>
				{isLogged && (
					<button className="ml-auto"
						onClick={() => { handleDelete(id) }}>Delete</button>
				)}
			</div>
			<div className="card-body">
				<div className="card-text">{record}</div>
			</div>
		</div>
	</div>);
};

const RecordsList = ({ props }) => {
	const { records, isLogged, handleDelete } = props;

	return (records.length !== 0 && <>{
		records
			.map(record => {
				return <Record key={record.id}
					props={{
						...record, isLogged,
						handleDelete
					}} />
			})
	}</>);
};


ReactDOM.render(
	<App />
	,
	document.getElementById('react')
);
