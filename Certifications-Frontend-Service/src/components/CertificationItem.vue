<template>
  <div class="card-padding">
    <v-card width="300">
      <v-img
        class="white--text align-end"
        height="120px"
        :src="'https://picsum.photos/id/' + this.imageLink + '/400/400'"
      >
        <v-card-title>{{ certification.name }}</v-card-title>
      </v-img>
      <div class="card-text-fix">
        <v-card-text class="align-end fill-height">
          <strong>
            {{ certification.price }} {{ certification.currency }}
          </strong>
          <router-link
            :to="'/edit-certificate-form/' + certification.id"
            tag="span"
            class="pointerClass"
          >
            <v-btn color="danger">
              <span>EDIT</span>
              <v-icon>mdi-pencil</v-icon>
            </v-btn>
          </router-link>
        </v-card-text>
      </div>
    </v-card>
  </div>
</template>

<script>
export default {
  data() {
    return {
      imageLink: ""
    };
  },
  props: {
    certification: {
      type: Object,
      require: true
    }
  },
  created() {
    if (this.certification.name.toLocaleLowerCase().includes("javascript")) {
      this.imageLink = "119";
    } else if (this.certification.name.toLocaleLowerCase().includes("java")) {
      this.imageLink = "1060";
    } else {
      this.imageLink = "20";
    }
  }
};
</script>

<style scoepd>
.card-padding {
  padding: 30px;
}
.card-text-fix {
  height: 50px;
}
</style>
