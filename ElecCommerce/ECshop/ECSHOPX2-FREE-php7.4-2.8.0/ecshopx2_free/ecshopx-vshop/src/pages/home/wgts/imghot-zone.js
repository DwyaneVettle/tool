import React, { Component } from 'react'
import { View, Text } from '@tarojs/components'
import { SpImage } from '@/components'
import { linkPage, classNames, styleNames, isString } from '@/utils'

import './imghot-zone.scss'

export default class WgtImgHotZone extends Component {
  static options = {
    addGlobalClass: true
  }

  static defaultProps = {
    info: null
  }

  constructor(props) {
    super(props)

    this.state = {
      curIdx: 0
    }
  }

  handleClickItem = linkPage

  render() {
    const { info } = this.props
    const { curIdx } = this.state

    if (!info) {
      return null
    }

    const { config, base, data, distributor_id } = info
    const curContent = (data[curIdx] || {}).content

    return (
      <View
        className={classNames('wgt wgt-imghot-zone', {
          wgt__padded: base.padded
        })}
      >
        {base.title && (
          <View className='wgt-head'>
            <View className='wgt-hd'>
              <Text className='wgt-title'>{base.title}</Text>
              <Text className='wgt-subtitle'>{base.subtitle}</Text>
            </View>
          </View>
        )}

        <View className={`slider-wra wgt-body img-hotzone ${config.padded ? 'padded' : ''}`}>
          <SpImage img-class='img-hotzone_img' src={config.imgUrl} width='718' lazyLoad />
          {data.map((item, index) => {
            return (
              <View
                key={`${index}1`}
                className='img-hotzone_zone'
                style={styleNames({
                  width: `${item.widthPer * 100}%`,
                  height: `${item.heightPer * 100}%`,
                  top: `${item.topPer * 100}%`,
                  left: `${item.leftPer * 100}%`
                })}
                onClick={this.handleClickItem.bind(this, {
                  ...item,
                  distributor_id
                })}
              ></View>
            )
          })}
        </View>
      </View>
    )
  }
}
