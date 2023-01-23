import React, { Component, useState } from "react";
import { Navigate } from "react-router-dom";
import AuthService from "../services/auth.service";
import {
  Inject,
  ScheduleComponent,
  Day,
  Week,
  WorkWeek,
  Month,
  Agenda,
  EventSettingsModel,
  ViewsDirective,
  ViewDirective,
} from "@syncfusion/ej2-react-schedule";
import { DataManager, WebApiAdaptor } from "@syncfusion/ej2-data";
import "../css/kalendar.css";
import customData from "../kalendar-resources/localeKalendar.json";
import 'react-datepicker/dist/react-datepicker.css'
import ReactDatePicker from "react-datepicker";

import { getCalendarByID} from "../services/instruktor.service";
// import { loadCldr } from '@syncfusion/ej2-base';
// import * as numberingSystems from '../kalendar-resources/numberingSystems.json';
// import * as gregorian from '../kalendar-resources/ca-gregorian.json';
// import * as numbers from '../kalendar-resources/numbers.json';
// import * as timeZoneNames from '../kalendar-resources/timeZoneNames.json';
// loadCldr(numberingSystems, gregorian, numbers, timeZoneNames);

import { L10n, loadCldr } from "@syncfusion/ej2-base";

loadCldr(
  require("cldr-data/supplemental/numberingSystems.json"),
  require("cldr-data/main/hr/ca-gregorian.json"),
  require("cldr-data/main/hr/numbers.json"),
  require("cldr-data/main/hr/timeZoneNames.json")
);

L10n.load(customData);

const InstructorCalendarView = (props) => {
  const element = props;
  const dataSource = element.dataSource;
  // const localData = {
  //    dataSource: [
  //      {
  //        endTime: new Date(2022, 12, 19, 18, 30),
  //        startTime: new Date(2022, 12, 19, 16, 0),
  //        IsReadonly: true
  //      },
  //      {
  //        endTime: new Date(2022, 12, 20, 8, 30),
  //        startTime: new Date(2022, 12, 20, 6, 0),
  //        IsReadonly: true
  //      },
  //   ],
  //   fields: {
  //     subject: { name: "Summary", default: "Dostupan" },
  //     startTime: { name: "startTime" },
  //     endTime: { name: "endTime" },
  //   },
  // };

  const localDataNew = {
    dataSource,
    fields: {
      subject: { name: "Summary", default: "Dostupan" },
      startTime: { name: "startTime" },
      endTime: { name: "endTime" },
    },
  };

  function onPopupOpen(args) {
    args.cancel = true;
  };

  return (
  <div className="scheduler-container-view">
    <ScheduleComponent
      currentView="Week"
      selectedDate={new Date()}
      locale="hr"
      timeFormat="HH:mm"
      firstDayOfWeek={1}
      eventSettings={localDataNew}
      popupOpen={onPopupOpen}
    >
      <ViewsDirective>
        <ViewDirective
          option="Day"
          startHour="06:00"
          endHour="23:00"
          displayName="Dan"
        ></ViewDirective>
        <ViewDirective
          option="Week"
          startHour="06:00"
          endHour="23:00"
          displayName="Tjedan"
        ></ViewDirective>
        <ViewDirective
          option="workWeek"
          startHour="06:00"
          endHour="23:00"
          displayName="Radni tjedan"
        ></ViewDirective>
        <ViewDirective
          option="Month"
          showWeekNumber={true}
          displayName="Mjesec"
        ></ViewDirective>
      </ViewsDirective>
      <Inject services={[Day, Week, WorkWeek, Month]} />
    </ScheduleComponent>
    </div>
  );
};

export default class Kalendar extends Component {
  constructor(props) {
    super(props);

    this.state = {
      startDate: new Date(),
      redirect: null,
      userReady: false,
    };
  }

 

  componentDidMount() {
    const currentUser = AuthService.getCurrentUser();
    console.log(window.location.href.split("=").reverse()[0]);
    if (!currentUser) {
        this.setState({ redirect: "/home" });
      } else {
        getCalendarByID(window.location.href.split("=").reverse()[0]).then(
            (response) => {
              this.setState({
                foundTermini: response.data,
                notFound: false,
              });
            },
            (error) => {
              this.setState({
                notFound: true,
              });
            }
          );
      }
    this.setState({ currentUser: currentUser, userReady: true });
  }

  render() {
    if (this.state.redirect) {
      return <Navigate to={this.state.redirect} />;
    } else if (this.state.notFound) {
      return <Navigate to={"/instruktori"} />;
    } else {
      return <InstructorCalendarView {...this.state.foundTermini} />;
    }
  }
}
