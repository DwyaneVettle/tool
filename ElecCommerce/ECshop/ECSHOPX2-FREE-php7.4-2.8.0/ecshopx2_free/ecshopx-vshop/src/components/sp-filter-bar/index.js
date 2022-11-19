import React, { Component } from 'react'
import { View, Text } from '@tarojs/components'
import { connect } from 'react-redux'
import { classNames } from '@/utils'

import './index.scss'

export default class SpFilterBar extends Component {
  static options = {
    addGlobalClass: true
  }

  static defaultProps = {
    sort: {},
    current: 0,
    list: []
  }

  constructor(props) {
    super(props)

    const { current } = props
    this.state = {
      curIdx: current,
      sortOrder: null
    }
  }

  handleClickItem(idx, type = '') {
    const item = this.props.list[idx]
    let sortOrder = null

    if (item.sort) {
      sortOrder = idx === this.state.curIdx ? this.state.sortOrder * -1 : item.sort
    }

    this.setState({
      curIdx: idx,
      sortOrder
    })

    this.props.onChange({
      current: idx,
      sort: sortOrder,
      type
    })
  }

  render() {
    const { list, className, custom, color } = this.props
    const { sortOrder, curIdx } = this.state

    return (
      <View className={classNames('sp-filter-bar', className)}>
        <View className='filter-bar-body'>
          {custom &&
            list.map((item, idx) => (
              <View
                className={classNames('sp-filter-bar__item', {
                  active: curIdx === idx,
                  'sort-asc': item.sort && sortOrder > 0,
                  'sort-desc': item.sort && sortOrder < 0
                })}
                onClick={this.handleClickItem.bind(this, idx, item.type)}
                key={`sp-filter-bar-item__${idx}`}
              >
                <Text className='sp-filter-bar__item-text' style={curIdx === idx && { color }}>
                  {item.title}
                </Text>
                {item.icon && <Text className={classNames('iconfont', item.icon)}></Text>}
              </View>
            ))}
        </View>
        <View className='sp-filter-bar__extra'>{this.props.children}</View>
      </View>
    )
  }
}
