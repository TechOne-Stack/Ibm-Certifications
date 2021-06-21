<template>
  <div class="card-padding">
    <v-card width="300">
      <v-img
        class="white--text align-end"
        height="120px"
        src="https://cdn.vuetifyjs.com/images/cards/docks.jpg"
      >
        <v-card-title>{{ voucher.state }}</v-card-title>
      </v-img>
      <div class="card-text-fix">
        <v-card-text class="align-end fill-height">
          <strong>
            {{ voucher.voucherCode }}
          </strong>
        </v-card-text>
      </div>
    </v-card>
  </div>
</template>

<script>
export default {
  props: {
    voucher: {
      type: Object,
      require: true
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
