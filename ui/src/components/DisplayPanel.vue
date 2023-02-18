<template>
  <div class="displayPanel">
    <div style="text-align: center;">{{message}}</div>
    <div v-show="nursingRooms.length!=0">
      <a-list
        :grid="{ gutter: 16, xs: 1, sm: 1, md: 2, lg: 2, xl: 3, xxl: 3 }"
        :dataSource="nursingRooms"
      >
        <a-list-item slot="renderItem" slot-scope="nursingRoom">
          <a-card :title="nursingRoom.location" style="min-height: 75vh">
            <a-row>
              <a-col>
                <p>
                  <b>Address:</b>
                  {{nursingRoom.address}}
                </p>
                <p>
                  <b>Level:</b>
                  {{nursingRoom.direction}}
                </p>
                <p>
                  <b>Features</b>
                </p>
                <ul>
                  <li v-for="facility in nursingRoom.facilities" :key="facility">{{facility}}</li>
                </ul>
                <span v-if="nursingRoom.changingStation">
                  <img
                    class="featureIcon"
                    src="../assets/images/diaper_changing_station_icon_small.png"
                  >
                </span>
                <span v-if="nursingRoom.powerPoints">
                  <img class="featureIcon" src="../assets/images/power_point_icon.png">
                </span>
                <span v-if="nursingRoom.sink">
                  <img class="featureIcon" src="../assets/images/sink_icon_small.png">
                </span>
                <span v-if="nursingRoom.waterDispenser">
                  <img class="featureIcon" src="../assets/images/water_dispenser_small.png">
                </span>
              </a-col>
            </a-row>
            <div style="top: 60%">
              <img class="resultImg" :src="`/images/${nursingRoom.image}`">
            </div>
            <div style="display: flex; flex-direction: row; margin-top: 15px;">
              <a-icon style="display: inline; margin-right: 8px" type="like-o"/>0
              <a-icon style="display: inline; margin-right: 8px; margin-left: 8px" type="message"/>0
            </div>
          </a-card>
        </a-list-item>
      </a-list>
    </div>
  </div>
</template>

<script>
/* eslint-disable */
import axios from "axios";
import { List } from "ant-design-vue";

export default {
  name: "DisplayPanel",
  props: {
    nursingRooms: {
      type: Array,
      default: []
    },
    searchStr: {
      type: String,
      default: ""
    },
    message: {
      type: String,
      default: ""
    }
  },
  data() {
    return {
      pagination: {
        onChange: page => {
          console.log(page);
        },
        pageSize: 3
      }
    };
  }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.title {
  font-size: 60px;
  margin-top: 30px;
  margin-left: 90px;
  font-weight: bold;
  font-family: "Avenir";
  text-align: left;
}

.slogan {
  margin-top: 30px;
  margin-left: 90px;
  text-align: left;
  font-size: 20px;
}

.displayPanel {
  text-align: left;
  padding: 10px;
}
.resultRow {
  margin: 10px;
  box-shadow: 0 2px 1px -1px rgba(0, 0, 0, 0.2), 0 1px 1px 0 rgba(0, 0, 0, 0.14),
    0 1px 3px 0 rgba(0, 0, 0, 0.12);
  padding: 30px;
  background-color: white;
}

.resultImg {
  display: block;
  margin: 5px;
  max-width: 95%;
  height: 30vh;
}

.resultIcon {
  display: inline;
  margin-right: 8px;
}

.featureIcon {
  width: 30px;
  height: 30px;
  margin: 5px;
}
</style>
