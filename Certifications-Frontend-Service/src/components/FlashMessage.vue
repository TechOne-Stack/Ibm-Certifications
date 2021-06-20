<template>
  <div>
    <v-alert dense text type="success" v-show="notifications">
      {{ message }}
      <v-btn :loading="loading" class="ma-1" color="error" plain @click="hide"
        ><span class="mdi mdi-delete"></span
      ></v-btn>
    </v-alert>
  </div>
</template>

<script>
// import { mapGetters } from "vuex";

// export default {
//   data() {
//     return {
//       message: ""
//     };
//   },
//   methods: {
//     show() {
//       this.notifications = true;

//       setTimeout(() => {
//         this.hide();
//       }, 3000);
//     },
//     hide() {
//       this.notifications = false;
//       this.$store.state.commit("notificationsMutation", false);
//     }
//   },
//   computed: {
//     ...mapGetters(["notifications"])
//   }
// };
</script>
