<template>
<div>  
    <div v-show="nursingRooms.length===0 && !searchStr">
      <a href="."><img class="logo"
        style="margin-top: 10vh;"
        alt="Nursing Room App logo"
        src="../assets/images/logo6.jpg" /></a>
    </div>
    <div v-show="nursingRooms.length!=0 || searchStr" style="position: absolute; top: 0px; left: 0px;">
      <a href="."><img style="max-width: 90%; margin-top:10px" src="../assets/images/top-logo-transparent2.png" /></a>      
    </div>
    <div style="display: flex; flex-direction: row; justify-content: center; padding-top: 20px">      
      <a-input-search class="searchInput"
      placeholder="Search nursing rooms here..."
      @search="handleSearch" />    
      <a-tooltip placement="right">
        <template slot="title">Use my current location</template>
        <a-button
          style="margin-left: 8px"
          type="primary"
          shape="circle"
          icon="compass"
          @click="searchByCurrentLocation"
        ></a-button>
      </a-tooltip>
    </div>
    <div v-show="fetching" style="margin-top: 10px"><a-spin /></div>
  <DisplayPanel :nursingRooms="nursingRooms" :searchStr="searchStr" :message="message"/>
  </div>
        
</template>

<script>
/* eslint-disable */
import axios from "axios";
import { Select, Tooltip } from "ant-design-vue";
import DisplayPanel from "./DisplayPanel.vue";

function showNavigatorGeolocationError(error) {
  switch(error.code) {
    case error.PERMISSION_DENIED:
      alert("User denied the request for Geolocation.");
      break;
    case error.POSITION_UNAVAILABLE:
      alert("Location information is unavailable.");
      break;
    case error.TIMEOUT:
      alert("The request to get user location timed out.");
      break;
    case error.UNKNOWN_ERROR:
      alert("An unknown error occurred.");
      break;
  }
}

function getLocation() {
  return new Promise((resolve, reject) => {
    if (navigator.geolocation) {
      navigator.geolocation.getCurrentPosition(position => {
        let currentLocation = {
          latitude: position.coords.latitude,
          longitude: position.coords.longitude
        };
        resolve(currentLocation);
      }, showNavigatorGeolocationError);
    } else {
      reject("Geolocation is not supported by this browser.");
    }
  });
}

export default {
  name: "SearchPanel",
  components: {
    DisplayPanel
  },
  data() {
    return {
      nursingRooms: [],
      value: undefined,
      searchStr: "",
      fetching: false,
      message: ""
    };
  },
  methods: {
    handleSearch(value) {
      this.isInitial = false;
      this.searchStr = value;
      if (value.length <= 1 || !value) {
        this.nursingRooms = [];
        return;
      }
      //   console.log(`selected search ${value}`);
      this.fetching = true;
      axios
        .get(`/nursingrooms/query?str=${value}`)
        .then(response => {
          this.nursingRooms = response.data;
          this.fetching = false;          
          if(response.data.length===0){
            this.message = "Found no nursing rooms that matches the criteria";
          }else{
            this.message = "";
          }
          window.scrollTo(0, 0);
        })
        .catch(error => console.log(error));
    },
    searchByCurrentLocation() {
      this.fetching = true;
      getLocation().then(location =>
        axios
          .get(
            `/nursingrooms/getByCoordinates?latitude=${
              location.latitude
            }&longitude=${location.longitude}`
          )
          .then(response => {
            this.nursingRooms = response.data;
            this.fetching = false;
          })
          .catch(error => console.log(error))
      );
    }
  }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.searchInput {
  width: 400px;
  max-width: 70%;
  font-size: 16px;
}

@media (max-width: 600px) {
  .logo {
    max-width: 90%;
  }
}

@media (min-width: 600px) {
  .logo {
    max-width: 60%;
  }
}
</style>
