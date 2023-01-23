import React, { Component, useState } from "react";
import * as ReactDOM from "react-dom";
import { Navigate, Link } from "react-router-dom";
import AuthService from "../services/auth.service";
import { getTestovi } from "../services/instruktor.service";
import "../App.css";

const TestoviView = (props) => {
  console.log(props);
  console.log("sdsadsassd");
  const pitanja = props.pitanja;
  console.log(pitanja);
  const renderQuestions = (pitanja) => {
    if (!pitanja) {
      return <div>Loading...</div>;
    }
    if (Array.isArray(pitanja)) {
      return (
        <div className="container">
          <header className="jumbotron">
            <div>
              {pitanja.map((question, index) => (
                <div key={index}>
                  <h3>{question.pitanje}</h3>
                  <div>
                    {question.odgovori.map((answer, i) => (
                      <div key={i} className="answer-container">
                        <input
                          type="radio"
                          name={`question_${index}`}
                          id={`question_${index}_answer_${i}`}
                          value={answer}
                          className="answer-input"
                        />
                        <label
                          htmlFor={`question_${index}_answer_${i}`}
                          className="answer-label"
                        >
                          {answer}
                        </label>
                      </div>
                    ))}
                  </div>
                </div>
              ))}
            </div>
          </header>
        </div>
      );
    }
    return <div>Error, expected prop of type Array</div>;
  };
  return <div>{renderQuestions(pitanja)}</div>;
};

export default class Testovi extends Component {
  constructor(props) {
    super(props);

    this.state = {
      redirect: null,
      userReady: false,
    };
  }

  componentDidMount() {
    let currentUser = AuthService.getCurrentUser();
    this.setState();

    if (!currentUser) {
      this.setState({ redirect: "/home" });
    } else {
      getTestovi().then(
        (response) => {
          this.setState({
            user: response.data,
            notFound: false,
          });
        },
        (error) => {
          this.setState({
            notFound: true,
          });
        }
      );

      this.setState({ currentUser: currentUser, userReady: true });
    }
  }

  render() {
    if (this.state.redirect) {
      return <Navigate to={this.state.redirect} />;
    } else if (this.state.notFound) {
      return <Navigate to={"/instruktori"} />;
    } else {
      return <TestoviView {...this.state.user} />;
    }
  }
}
